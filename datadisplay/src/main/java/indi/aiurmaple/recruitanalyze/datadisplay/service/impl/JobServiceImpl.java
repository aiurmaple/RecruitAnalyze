package indi.aiurmaple.recruitanalyze.datadisplay.service.impl;

import indi.aiurmaple.recruitanalyze.datadisplay.dao.JobDao;
import indi.aiurmaple.recruitanalyze.datadisplay.model.JobCityModel;
import indi.aiurmaple.recruitanalyze.datadisplay.model.JobExpModel;
import indi.aiurmaple.recruitanalyze.datadisplay.service.JobService;
import indi.aiurmaple.recruitanalyze.datadisplay.util.Quarter;
import indi.aiurmaple.recruitanalyze.datadisplay.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    private static final String SALARY_TABLE = "getJobsSalaryByCity";

    private static final String SALARY_EXP_TABLE = "getJobsSalaryByExp";

    private static final long EXPIRETIME = 1L;


    @Override
    public Integer getJobsNum(Boolean time) {
        return time ? jobDao.getJobsNum(time, TimeUtil.getBeforeDate()): jobDao.getJobsNum(time, TimeUtil.getCurDate());
    }

    @Override
    public List<Integer> getJobsNumByCity(Integer[] cityIds) {
        return jobDao.getJobsNumByCity(cityIds, TimeUtil.getCurDate());
    }

    @Override
    public Map<String, List<Integer>> getJobsSalaryByCity(Integer jobNameId, Integer[] cityIds, Quarter[] quarters) {
        String jobNameIdStr = String.valueOf(jobNameId);
        if (hashOperations.hasKey(SALARY_TABLE, jobNameIdStr)) {
            return (Map<String, List<Integer>>) hashOperations.get(SALARY_TABLE, jobNameIdStr);
        } else {
            Map<String, List<Integer>> salaryMap = new HashMap<>();
            for (int i = 0; i < quarters.length; i++) {
                List<String> quarterTime = quarters[i].getQuarterTime();
                String startTime = quarterTime.get(0);
                String endTime = quarterTime.get(1);
                List<JobCityModel> salaryList = jobDao.getJobsSalaryByCity(jobNameId, cityIds, startTime, endTime);
                List<Integer> avgSalaryByCityList = handlerSalaryCity(salaryList);
                if (avgSalaryByCityList == null || avgSalaryByCityList.size() == 0) {
                    avgSalaryByCityList = new LinkedList<>();
                    for (Integer ignored : cityIds) {
                        avgSalaryByCityList.add(0);
                    }
                }
                salaryMap.put(String.valueOf(i + 1), avgSalaryByCityList);
            }
            hashOperations.put(SALARY_TABLE, jobNameIdStr, salaryMap);
            hashOperations.getOperations().expire(SALARY_TABLE, EXPIRETIME, TimeUnit.DAYS);
            return salaryMap;
        }
    }

    @Override
    public List<Integer> getJobsSalaryByExp(Integer jobNameId, Integer[] workingExpIds) {
        String jobNameIdStr = String.valueOf(jobNameId);
        if (hashOperations.hasKey(SALARY_EXP_TABLE, jobNameIdStr)) {
            return (List<Integer>) hashOperations.get(SALARY_EXP_TABLE, jobNameIdStr);
        } else {
            List<JobExpModel> salaryList = jobDao.getJobsSalaryByExp(jobNameId, workingExpIds, TimeUtil.getCurDate());
            List<Integer> cachedList = handlerSalaryExp(salaryList);
            hashOperations.put(SALARY_EXP_TABLE, jobNameIdStr, cachedList);
            hashOperations.getOperations().expire(SALARY_EXP_TABLE, EXPIRETIME, TimeUnit.DAYS);
            return cachedList;
        }
    }

    @Override
    public List<Integer> getJobsNumByEdu(Integer jobNameId, Integer[] eduLevelIds) {
        return jobDao.getJobsNumByEdu(jobNameId, eduLevelIds, TimeUtil.getCurDate());
    }

    @Override
    public List<Integer> getJobsNumByQuarter(Integer jobNameId) {
        int month = TimeUtil.getMonth();
        String year = String.valueOf(TimeUtil.getYear());
        String[] times = new String[month];
        StringBuilder startDate = new StringBuilder(year);
        if (month <= 6) {
            startDate.append("-01-01");
            for (int i = 1; i <= month; i++) {
                times[i-1] = year + "-0" + i + "-31";
            }
        } else {
            startDate.append("-07-01");
            for (int i = 7; i <= month; i++) {
                times[i-7] = year + "-" + i + "-31";
            }
        }
        return jobDao.getJobsNumByQuarter(jobNameId, startDate.toString(), times);
    }

    private List<Integer> handlerSalaryCity(List<JobCityModel> salaryList) {
        List<JobCityModel> avgSalaryList = new LinkedList<>();
        for (JobCityModel salaryEntity : salaryList) {
            String salary = salaryEntity.getSalary();
            if (!salary.equals("薪资面议") && !salary.equals("校招")) {
                String[] salaryStr = salary.split("-");
                Integer avgSalary = averaging(salaryStr);
                JobCityModel jobEntity = new JobCityModel();
                jobEntity.setSalary(String.valueOf(avgSalary));
                jobEntity.setCityId(salaryEntity.getCityId());
                avgSalaryList.add(jobEntity);
            }
        }
        Integer preId = 0;
        int sum = 0;
        int count = 0;
        int total = 0;
        int length = avgSalaryList.size();
        List<Integer> avgSalaryByCityList = new LinkedList<>();
        for (JobCityModel salaryEntity : avgSalaryList) {
            if (++total == length) {
                sum += Integer.valueOf(salaryEntity.getSalary());
                count++;
                avgSalaryByCityList.add(sum / count);
                return avgSalaryByCityList;
            }
            if (!preId.equals(0) && !preId.equals(salaryEntity.getCityId())) {
                avgSalaryByCityList.add(sum / count);
                sum = 0;
                count = 0;
                sum += Integer.valueOf(salaryEntity.getSalary());
                count++;
                preId = salaryEntity.getCityId();
            } else {
                sum += Integer.valueOf(salaryEntity.getSalary());
                count++;
                preId = salaryEntity.getCityId();
            }
        }
        return avgSalaryByCityList;
    }

    private List<Integer> handlerSalaryExp(List<JobExpModel> salaryList) {
        List<JobExpModel> avgSalaryList = new LinkedList<>();
        for (JobExpModel salaryEntity : salaryList) {
            String salary = salaryEntity.getSalary();
            if (!salary.equals("薪资面议") && !salary.equals("校招")) {
                String[] salaryStr = salary.split("-");
                Integer avgSalary = averaging(salaryStr);
                JobExpModel jobEntity = new JobExpModel();
                jobEntity.setSalary(String.valueOf(avgSalary));
                jobEntity.setWorkingExpId(salaryEntity.getWorkingExpId());
                avgSalaryList.add(jobEntity);
            }
        }
        Integer preId = 0;
        int sum = 0;
        int count = 0;
        int total = 0;
        int length = avgSalaryList.size();
        List<Integer> avgSalaryByCityList = new LinkedList<>();
        for (JobExpModel salaryEntity : avgSalaryList) {
            if (++total == length) {
                sum += Integer.valueOf(salaryEntity.getSalary());
                count++;
                avgSalaryByCityList.add(sum / count);
                return avgSalaryByCityList;
            }
            if (!preId.equals(0) && !preId.equals(salaryEntity.getWorkingExpId())) {
                avgSalaryByCityList.add(sum / count);
                sum = 0;
                count = 0;
                sum += Integer.valueOf(salaryEntity.getSalary());
                count++;
                preId = salaryEntity.getWorkingExpId();
            } else {
                sum += Integer.valueOf(salaryEntity.getSalary());
                count++;
                preId = salaryEntity.getWorkingExpId();
            }
        }
        return avgSalaryByCityList;
    }

    private Integer transformInt(String str) {
        if (str.contains("以下")) {
            str = str.replace("以下","");
        }
        if (str.contains(".")) {
            String newStr = str.replace(".", "").replace("K", "00");
            return Integer.valueOf(newStr);
        } else {
            String newStr = str.replace("K","000");
            return Integer.valueOf(newStr);
        }
    }

    private Integer averaging(String[] salaryStr) {
        if (salaryStr.length > 1) {
            Integer minSalary = transformInt(salaryStr[0]);
            Integer maxSalary = transformInt(salaryStr[1]);
            return (minSalary + maxSalary) / 2;
        } else {
            return transformInt(salaryStr[0]);
        }
    }
}

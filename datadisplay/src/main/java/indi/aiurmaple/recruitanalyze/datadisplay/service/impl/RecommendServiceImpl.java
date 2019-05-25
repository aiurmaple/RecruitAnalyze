package indi.aiurmaple.recruitanalyze.datadisplay.service.impl;

import indi.aiurmaple.recruitanalyze.datadisplay.dao.RecommendDao;
import indi.aiurmaple.recruitanalyze.datadisplay.entity.EduLevelEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.entity.JobNameEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.entity.WorkingExpEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.model.CityRankingModel;
import indi.aiurmaple.recruitanalyze.datadisplay.model.JobRankingModel;
import indi.aiurmaple.recruitanalyze.datadisplay.model.JobSalaryModel;
import indi.aiurmaple.recruitanalyze.datadisplay.service.EduLevelService;
import indi.aiurmaple.recruitanalyze.datadisplay.service.JobNameService;
import indi.aiurmaple.recruitanalyze.datadisplay.service.RecommendService;
import indi.aiurmaple.recruitanalyze.datadisplay.service.WorkingExpService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private JobNameService jobNameService;

    @Autowired
    private EduLevelService eduLevelService;

    @Autowired
    private WorkingExpService workingExpService;

    @Autowired
    private RecommendDao recommendDao;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    private static final String SALARY_KEY = "getSalaryRanking";

    private static final String EDU_KEY = "getEduLevelRanking";

    private static final String WORKING_KEY = "getJobRankingByWorkingExp";

    private static final long EXPIRETIME = 1L;

    @Override
    public List<Integer> getSalaryRanking() {
        if (valueOperations.getOperations().hasKey(SALARY_KEY)) {
            return (List<Integer>) valueOperations.get(SALARY_KEY);
        } else {
            List<JobNameEntity> jobList = jobNameService.getAll();
            List<JobSalaryModel> jobSalaryModelList = new LinkedList<>();
            for(JobNameEntity job : jobList) {
                Integer jobNameId = job.getId();
                List<String> salaryList = recommendDao.getSalaryByJobName(jobNameId);
                Integer avg = averge(salaryList);
                JobSalaryModel jobSalaryModel = new JobSalaryModel(avg, jobNameId);
                jobSalaryModelList.add(jobSalaryModel);
            }
            jobSalaryModelList.sort(Comparator.comparing(JobSalaryModel::getSalary));
            List<Integer> integerList = new LinkedList<>();
            jobSalaryModelList.forEach((job) -> integerList.add(job.getJobNameId()));
            valueOperations.set(SALARY_KEY, integerList);
            valueOperations.getOperations().expire(SALARY_KEY, EXPIRETIME, TimeUnit.DAYS);
            return integerList;
        }
    }

    @Override
    public List<Integer> getEduLevelRanking() {
        if (valueOperations.getOperations().hasKey(EDU_KEY)) {
            return (List<Integer>) valueOperations.get(EDU_KEY);
        }else {
            List<EduLevelEntity> eduLevelEntities = eduLevelService.getAll();
            Map<Integer, Integer> rankingMap = new HashMap<>();
            for (EduLevelEntity eduLevelEntity : eduLevelEntities) {
                List<JobRankingModel> jobRankingModels = recommendDao.getRankingByEduLevel(eduLevelEntity.getId());
                for (int i = 0; i < jobRankingModels.size(); i++) {
                    Integer jobNameId = jobRankingModels.get(i).getJobNameId();
                    rankingMap.putIfAbsent(jobNameId, 0);
                    rankingMap.put(jobNameId,rankingMap.get(jobNameId) + (i + 1));
                }
            }
            List<JobRankingModel> sortList = new LinkedList<>();
            rankingMap.forEach((key, value) -> sortList.add(new JobRankingModel(value,key)));
            sortList.sort(Comparator.comparing(JobRankingModel::getSum));
            List<Integer> ranking = new LinkedList<>();
            sortList.forEach((entity) -> ranking.add(entity.getJobNameId()));
            valueOperations.set(EDU_KEY, ranking);
            valueOperations.getOperations().expire(EDU_KEY, EXPIRETIME, TimeUnit.DAYS);
            return ranking;
        }
    }

    @Override
    public List<Integer> getJobRanking() {
        List<Integer> ranking = new LinkedList<>();
        List<JobRankingModel> jobRanking = recommendDao.getJobRanking();
        jobRanking.forEach((entity) -> {
            ranking.add(entity.getJobNameId());
        });
        return ranking;
    }

    @Override
    public List<Integer> getJobRankingByCity() {
        List<CityRankingModel> cityRankingModels = recommendDao.getCityRankingByJobNum();
        Map<Integer, Integer> rankingMap = new HashMap<>();
        for (CityRankingModel cityEntity : cityRankingModels) {
            List<JobRankingModel> jobRankingModels = recommendDao.getRankingByCity(cityEntity.getCityId());
            for (int i = 0; i < jobRankingModels.size(); i++) {
                Integer jobNameId = jobRankingModels.get(i).getJobNameId();
                rankingMap.putIfAbsent(jobNameId, 0);
                rankingMap.put(jobNameId,rankingMap.get(jobNameId) + (i + 1));
            }
        }
        List<JobRankingModel> sortList = new LinkedList<>();
        rankingMap.forEach((key, value) -> sortList.add(new JobRankingModel(value,key)));
        sortList.sort(Comparator.comparing(JobRankingModel::getSum));
        List<Integer> ranking = new LinkedList<>();
        sortList.forEach((entity) -> ranking.add(entity.getJobNameId()));
        return ranking;
    }

    @Override
    public List<Integer> getJobRankingByWorkingExp() {
        if (valueOperations.getOperations().hasKey(WORKING_KEY)) {
            return (List<Integer>) valueOperations.get(WORKING_KEY);
        } else {
            List<WorkingExpEntity> workingExpEntities = workingExpService.getAll();
            Map<Integer, Integer> rankingMap = new HashMap<>();
            for (WorkingExpEntity workingExpEntity : workingExpEntities) {
                List<JobRankingModel> jobRankingModels = recommendDao.getRankingByWorkingExp(workingExpEntity.getId());
                for (int i = 0; i < jobRankingModels.size(); i++) {
                    Integer jobNameId = jobRankingModels.get(i).getJobNameId();
                    rankingMap.putIfAbsent(jobNameId, 0);
                    rankingMap.put(jobNameId,rankingMap.get(jobNameId) + (i + 1));
                }
            }
            List<JobRankingModel> sortList = new LinkedList<>();
            rankingMap.forEach((key, value) -> sortList.add(new JobRankingModel(value,key)));
            sortList.sort(Comparator.comparing(JobRankingModel::getSum));
            List<Integer> ranking = new LinkedList<>();
            sortList.forEach((entity) -> ranking.add(entity.getJobNameId()));
            valueOperations.set(WORKING_KEY, ranking);
            valueOperations.getOperations().expire(WORKING_KEY, EXPIRETIME, TimeUnit.DAYS);
            return ranking;
        }
    }

    private Integer averge(List<String> salaryList) {
        List<Integer> avgSalaryList = new LinkedList<>();
        for (String salary : salaryList) {
            if (!salary.equals("薪资面议") && !salary.equals("校招")) {
                String[] salaryStr = salary.split("-");
                Integer avgSalary = averaging(salaryStr);
                avgSalaryList.add(avgSalary);
            }
        }
        Integer sum = 0;
        for(Integer num : avgSalaryList) {
            sum += num;
        }
        return sum / (avgSalaryList.size());
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
}

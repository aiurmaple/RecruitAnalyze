package indi.aiurmaple.recruitanalyze.datatransform.runner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.aiurmaple.recruitanalyze.datatransform.dao.*;
import indi.aiurmaple.recruitanalyze.datatransform.entity.*;
import indi.aiurmaple.recruitanalyze.datatransform.utils.DataUtils;
import indi.aiurmaple.recruitanalyze.datatransform.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ReptileRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ReptileRunner.class);
    private static final String ARG = "path";
    private static final String TABLENAME = "job";

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private SequenceRepository sequenceJobRepository;
    @Autowired
    private WelfareRepository welfareRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanySizeRepository companySizeRepository;
    @Autowired
    private CompanyTypeRepository companyTypeRepository;
    @Autowired
    private EduLevelRepository eduLevelRepository;
    @Autowired
    private JobNameRepository jobNameRepository;
    @Autowired
    private WorkingExpRepository workingExpRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        File dir = argCheck(args);
        List<File> jsonFiles = FileUtils.filterSuffix(dir, "json");
        log.debug("start transform data");
        jsonFiles.forEach(file -> {
            String fileName = file.getName();
            log.debug("the file is " + file.getName());
            String content = FileUtils.readFile(file);
            transformData(content, fileName);
        });
    }

    private File argCheck(ApplicationArguments args) {
        boolean isRequired = args.containsOption(ARG);
        if (!isRequired) {
            log.error("the param path is required to start the program !!!");
            throw new IllegalArgumentException("the param path is required to start the program !!!");
        }
        String filePath = args.getOptionValues(ARG).get(0);
        return new File(filePath);
    }

    private void transformData(String content, String fileName) {
        String[] datas = content.split("\n");
        for(String data : datas) {
            JSONArray jsonArray = JSONArray.parseArray(data);
            for (Object obj : jsonArray) {
                JSONObject root = (JSONObject) obj;
                JobEntity jobEntity = new JobEntity();
                Long jobId = sequenceJobRepository.findByTableName(TABLENAME).getNextId();
                jobEntity.setId(jobId);
                jobEntity.setJobNumber(root.getString("number"));
                jobEntity.setSalary(root.getString("salary"));
                jobEntity.setEmplType(root.getString("emplType"));
                jobEntity.setRecruitCount(root.getInteger("recruitCount"));
                jobEntity.setCreateDate(Timestamp.valueOf(root.getString("createDate")));
                jobEntity.setEndDate(Timestamp.valueOf(root.getString("endDate")));
                jobEntity.setJobNameId(getJobNameId(fileName));
                jobEntity.setWorkingExpId(getWorkingExpId(root));
                jobEntity.setEduLevelId(getEduLevelId(root));
                jobEntity.setCompanyId(getCompanyId(root));
                jobEntity.setCityId(getCityId(fileName));
                jobEntity.setWelfares(getWelfares(root));
                jobRepository.save(jobEntity);
                sequenceJobRepository.save(new SequenceEntity(1, TABLENAME, ++jobId));
            }
        }
    }

    private Integer getJobNameId(String fileName) {
        String jobName = DataUtils.handleJobName(fileName);
        JobNameEntity jobNameEntity = jobNameRepository.findByJobLabel(jobName);
        Integer jobNameId;
        if (jobNameEntity != null) {
            jobNameId = jobNameEntity.getId();
        } else {
            jobNameId = jobNameRepository.save(new JobNameEntity(jobName)).getId();
        }
        return jobNameId;
    }

    private Integer getWorkingExpId(JSONObject root) {
        String workExp = DataUtils.handleWorkingExp(root);
        WorkingExpEntity workingExpEntity = workingExpRepository.findByWorkingLabel(workExp);
        Integer workExpId;
        if (workingExpEntity != null) {
            workExpId = workingExpEntity.getId();
        } else {
            workExpId = workingExpRepository.save(new WorkingExpEntity(workExp)).getId();
        }
        return workExpId;
    }

    private Integer getEduLevelId(JSONObject root) {
        String eduLevel = DataUtils.handleEduLevel(root);
        EduLevelEntity eduLevelEntity = eduLevelRepository.findByEduLabel(eduLevel);
        Integer eduLevelId;
        if (eduLevelEntity != null) {
            eduLevelId = eduLevelEntity.getId();
        } else {
            eduLevelId = eduLevelRepository.save(new EduLevelEntity(eduLevel)).getId();
        }
        return eduLevelId;
    }

    private Integer getCityId(String fileName) {
        String city = DataUtils.handleCity(fileName);
        CityEntity cityEntity = cityRepository.findByCityLabel(city);
        Integer cityId;
        if (cityEntity != null) {
            cityId = cityEntity.getId();
        } else {
            cityId = cityRepository.save(new CityEntity(city)).getId();
        }
        return cityId;
    }

    private Long getCompanyId(JSONObject root) {
        JSONObject company = root.getJSONObject("company");
        String companyNumber = company.getString("number");
        CompanyEntity companyEntity = companyRepository.findByCompanyNumber(companyNumber);
        Long companyId;
        if (companyEntity != null) {
            companyId = companyEntity.getId();
        } else {
            CompanyEntity entity = new CompanyEntity();
            entity.setCompanyNumber(company.getString("number"));
            entity.setCompanyName(company.getString("name"));
            entity.setCompanySizeId(getCompanySizeId(company));
            entity.setCompanyTypeId(getCompanyTypeId(company));
            companyId = companyRepository.save(entity).getId();
        }
        return companyId;
    }

    private Integer getCompanySizeId(JSONObject root) {
        String companySize = DataUtils.handleCompanySize(root);
        CompanySizeEntity companySizeEntity = companySizeRepository.findBySizeLabel(companySize);
        Integer companySizeId;
        if (companySizeEntity != null) {
            companySizeId = companySizeEntity.getId();
        } else {
            companySizeId = companySizeRepository.save(new CompanySizeEntity(companySize)).getId();
        }
        return companySizeId;
    }

    private Integer getCompanyTypeId(JSONObject root) {
        String companyType = DataUtils.handleCompanyType(root);
        CompanyTypeEntity companyTypeEntity = companyTypeRepository.findByTypeLabel(companyType);
        Integer companyTypeId;
        if (companyTypeEntity != null) {
            companyTypeId = companyTypeEntity.getId();
        } else {
            companyTypeId = companyTypeRepository.save(new CompanyTypeEntity(companyType)).getId();
        }
        return companyTypeId;
    }

    private Set<WelfareEntity> getWelfares(JSONObject root) {
        List<String> welfares = DataUtils.handleWelfares(root);
        Set<WelfareEntity> welfareEntities = new HashSet<>();
        for (String welfare : welfares) {
            WelfareEntity welfareEntity = welfareRepository.findByWelfareLabel(welfare);
            Integer welfareId;
            if (welfareEntity != null) {
                welfareId = welfareEntity.getId();
            } else {
                welfareId = welfareRepository.save(new WelfareEntity(welfare)).getId();
            }
            welfareEntities.add(new WelfareEntity(welfareId, welfare));
        }
        return welfareEntities;
    }
}

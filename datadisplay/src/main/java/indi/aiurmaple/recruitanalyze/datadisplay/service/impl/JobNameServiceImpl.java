package indi.aiurmaple.recruitanalyze.datadisplay.service.impl;

import indi.aiurmaple.recruitanalyze.datadisplay.dao.JobNameDao;
import indi.aiurmaple.recruitanalyze.datadisplay.entity.JobNameEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.service.JobNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobNameServiceImpl implements JobNameService {
    @Autowired
    private JobNameDao jobNameDao;

    @Override
    public List<JobNameEntity> getAll() {
        return jobNameDao.getAll();
    }
}

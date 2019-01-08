package indi.aiurmaple.recruitanalyze.datadisplay.service.impl;

import indi.aiurmaple.recruitanalyze.datadisplay.dao.JobDao;
import indi.aiurmaple.recruitanalyze.datadisplay.entity.JobEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;

    @Override
    public List<JobEntity> getAll() {
        return jobDao.getAll();
    }
}

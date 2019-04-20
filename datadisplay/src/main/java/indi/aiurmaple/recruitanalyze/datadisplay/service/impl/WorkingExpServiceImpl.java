package indi.aiurmaple.recruitanalyze.datadisplay.service.impl;

import indi.aiurmaple.recruitanalyze.datadisplay.dao.WorkingExpDao;
import indi.aiurmaple.recruitanalyze.datadisplay.entity.WorkingExpEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.service.WorkingExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingExpServiceImpl implements WorkingExpService {
    @Autowired
    private WorkingExpDao workingExpDao;

    @Override
    public List<WorkingExpEntity> getAll() {
        return workingExpDao.getAll();
    }
}

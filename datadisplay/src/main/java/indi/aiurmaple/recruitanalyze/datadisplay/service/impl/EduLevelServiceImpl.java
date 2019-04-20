package indi.aiurmaple.recruitanalyze.datadisplay.service.impl;

import indi.aiurmaple.recruitanalyze.datadisplay.dao.EduLevelDao;
import indi.aiurmaple.recruitanalyze.datadisplay.entity.EduLevelEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.service.EduLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduLevelServiceImpl implements EduLevelService{
    @Autowired
    private EduLevelDao eduLevelDao;

    @Override
    public List<EduLevelEntity> getAll() {
        return eduLevelDao.getAll();
    }
}

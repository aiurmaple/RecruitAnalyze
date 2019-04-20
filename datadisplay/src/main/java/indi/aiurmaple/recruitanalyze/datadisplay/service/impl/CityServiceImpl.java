package indi.aiurmaple.recruitanalyze.datadisplay.service.impl;

import indi.aiurmaple.recruitanalyze.datadisplay.dao.CityDao;
import indi.aiurmaple.recruitanalyze.datadisplay.entity.CityEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    public List<CityEntity> getAll() {
        return cityDao.getAll();
    }
}

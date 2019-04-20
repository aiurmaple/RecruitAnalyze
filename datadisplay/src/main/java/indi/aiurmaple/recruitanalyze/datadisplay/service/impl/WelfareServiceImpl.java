package indi.aiurmaple.recruitanalyze.datadisplay.service.impl;

import indi.aiurmaple.recruitanalyze.datadisplay.dao.WelfareDao;
import indi.aiurmaple.recruitanalyze.datadisplay.model.WelfareReponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.service.WelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class WelfareServiceImpl implements WelfareService {
    @Autowired
    private WelfareDao welfareDao;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    private static final String WELFARE_TABLE = "getWelfareNumByJob";

    private static final long EXPIRETIME = 1L;

    @Override
    public List<WelfareReponseModel> getWelfareNumByJob(Integer jobNameId) {
        String jobNameIdStr = String.valueOf(jobNameId);
        if (hashOperations.hasKey(WELFARE_TABLE, jobNameIdStr)) {
            return (List<WelfareReponseModel>) hashOperations.get(WELFARE_TABLE, jobNameIdStr);
        } else {
            Date curDate = new Date(System.currentTimeMillis());
            List<WelfareReponseModel> cachedList = welfareDao.getWelfareNumByJob(jobNameId, curDate.toString());
            hashOperations.put(WELFARE_TABLE, jobNameIdStr, cachedList);
            hashOperations.getOperations().expire(WELFARE_TABLE, EXPIRETIME, TimeUnit.DAYS);
            return cachedList;
        }
    }
}

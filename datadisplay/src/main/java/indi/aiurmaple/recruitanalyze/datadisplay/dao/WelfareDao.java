package indi.aiurmaple.recruitanalyze.datadisplay.dao;

import indi.aiurmaple.recruitanalyze.datadisplay.model.WelfareReponseModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WelfareDao {
    List<WelfareReponseModel> getWelfareNumByJob(@Param("jobNameId") Integer jobNameId, @Param("date") String date);
}

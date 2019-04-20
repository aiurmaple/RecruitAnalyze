package indi.aiurmaple.recruitanalyze.datadisplay.service;

import indi.aiurmaple.recruitanalyze.datadisplay.model.WelfareReponseModel;

import java.util.List;

public interface WelfareService {
    List<WelfareReponseModel> getWelfareNumByJob(Integer jobNameId);
}

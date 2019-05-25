package indi.aiurmaple.recruitanalyze.datadisplay.service;

import indi.aiurmaple.recruitanalyze.datadisplay.util.Quarter;

import java.util.List;
import java.util.Map;

public interface JobService {
    Integer getJobsNum(Boolean time);

    List<Integer> getJobsNumByCity(Integer[] cityIds);

    Map<String, List<Integer>> getJobsSalaryByCity(Integer jobNameId, Integer[] cityIds, Quarter[] quarter);

    List<Integer> getJobsSalaryByExp(Integer jobNameId, Integer[] workingExpIds);

    List<Integer> getJobsNumByEdu(Integer jobNameId, Integer[] eduLevelIds);

    List<Integer> getJobsNumByQuarter(Integer jobNameId);

    List<Integer> getJobsNumByCity(Integer cityId);
}

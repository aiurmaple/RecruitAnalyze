package indi.aiurmaple.recruitanalyze.datadisplay.dao;

import indi.aiurmaple.recruitanalyze.datadisplay.model.CityRankingModel;
import indi.aiurmaple.recruitanalyze.datadisplay.model.JobRankingModel;

import java.util.List;

public interface RecommendDao {
    List<String> getSalaryByJobName(Integer jobNameId);

    List<JobRankingModel> getRankingByEduLevel(Integer eduLevelId);

    List<JobRankingModel> getJobRanking();

    List<CityRankingModel> getCityRankingByJobNum();

    List<JobRankingModel> getRankingByCity(Integer cityId);

    List<JobRankingModel> getRankingByWorkingExp(Integer workingExpId);
}

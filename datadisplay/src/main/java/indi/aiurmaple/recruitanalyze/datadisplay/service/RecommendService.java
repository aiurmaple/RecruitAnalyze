package indi.aiurmaple.recruitanalyze.datadisplay.service;

import java.util.List;

public interface RecommendService {
    List<Integer> getSalaryRanking();

    List<Integer> getEduLevelRanking();

    List<Integer> getJobRanking();

    List<Integer> getJobRankingByCity();

    List<Integer> getJobRankingByWorkingExp();
}

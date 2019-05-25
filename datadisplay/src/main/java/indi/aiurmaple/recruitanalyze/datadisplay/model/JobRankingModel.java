package indi.aiurmaple.recruitanalyze.datadisplay.model;

import lombok.Data;

@Data
public class JobRankingModel {
    private Integer sum;
    private Integer jobNameId;

    public JobRankingModel() {

    }

    public JobRankingModel(Integer sum, Integer jobNameId) {
        this.sum = sum;
        this.jobNameId = jobNameId;
    }
}

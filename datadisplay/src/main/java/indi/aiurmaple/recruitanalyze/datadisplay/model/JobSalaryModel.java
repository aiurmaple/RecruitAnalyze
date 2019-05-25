package indi.aiurmaple.recruitanalyze.datadisplay.model;

import lombok.Data;

@Data
public class JobSalaryModel {
    private Integer salary;
    private Integer jobNameId;

    public JobSalaryModel() {

    }

    public JobSalaryModel(Integer salary, Integer jobNameId) {
        this.salary = salary;
        this.jobNameId = jobNameId;
    }
}

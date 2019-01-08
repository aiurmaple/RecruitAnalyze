package indi.aiurmaple.recruitanalyze.datadisplay.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class JobEntity {
    private Long id;
    private String jobNumber;
    private Integer jobNameId;
    private String salary;
    private String emplType;
    private Integer recruitCount;
    private Integer workingExpId;
    private Integer eduLevelId;
    private Long companyId;
    private Integer cityId;
    private Timestamp createDate;
    private Timestamp endDate;
}

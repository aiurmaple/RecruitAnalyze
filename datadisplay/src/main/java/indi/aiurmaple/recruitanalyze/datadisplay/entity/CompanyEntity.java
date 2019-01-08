package indi.aiurmaple.recruitanalyze.datadisplay.entity;

import lombok.Data;

@Data
public class CompanyEntity {
    private Long id;
    private String companyNumber;
    private String companyName;
    private Integer companySizeId;
    private Integer companyTypeId;
}

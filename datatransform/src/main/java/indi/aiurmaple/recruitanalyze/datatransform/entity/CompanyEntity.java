package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "recruit_analyze")
public class CompanyEntity {
    private Long id;
    private String companyNumber;
    private String companyName;
    private Integer companySizeId;
    private Integer companyTypeId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "company_number")
    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_size_id", nullable = false)
    public Integer getCompanySizeId() {
        return companySizeId;
    }

    public void setCompanySizeId(Integer companySizeId) {
        this.companySizeId = companySizeId;
    }

    @Basic
    @Column(name = "company_type_id", nullable = false)
    public Integer getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(Integer companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return id.equals(that.id) &&
                companySizeId.equals(that.companySizeId) &&
                companyTypeId.equals(that.companyTypeId) &&
                Objects.equals(companyNumber, that.companyNumber) &&
                Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyNumber, companyName, companySizeId, companyTypeId);
    }
}

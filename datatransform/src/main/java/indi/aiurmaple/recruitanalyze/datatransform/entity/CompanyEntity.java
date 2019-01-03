package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "recruit_analyze")
public class CompanyEntity {
    private long id;
    private String companyNumber;
    private String companyName;
    private int companySizeId;
    private int companyTypeId;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    @Column(name = "company_size_id")
    public int getCompanySizeId() {
        return companySizeId;
    }

    public void setCompanySizeId(int companySizeId) {
        this.companySizeId = companySizeId;
    }

    @Basic
    @Column(name = "company_type_id")
    public int getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(int companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return id == that.id &&
                companySizeId == that.companySizeId &&
                companyTypeId == that.companyTypeId &&
                Objects.equals(companyNumber, that.companyNumber) &&
                Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, companyNumber, companyName, companySizeId, companyTypeId);
    }
}

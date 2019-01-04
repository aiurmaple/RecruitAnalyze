package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "job", schema = "recruit_analyze")
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


    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "job_number", unique = true)
    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Basic
    @Column(name = "job_name_id", nullable = false)
    public Integer getJobNameId() {
        return jobNameId;
    }

    public void setJobNameId(Integer jobNameId) {
        this.jobNameId = jobNameId;
    }

    @Basic
    @Column(name = "salary")
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "empl_type")
    public String getEmplType() {
        return emplType;
    }

    public void setEmplType(String emplType) {
        this.emplType = emplType;
    }

    @Basic
    @Column(name = "recruit_count")
    public Integer getRecruitCount() {
        return recruitCount;
    }

    public void setRecruitCount(Integer recruitCount) {
        this.recruitCount = recruitCount;
    }

    @Basic
    @Column(name = "working_exp_id", nullable = false)
    public Integer getWorkingExpId() {
        return workingExpId;
    }

    public void setWorkingExpId(Integer workingExpId) {
        this.workingExpId = workingExpId;
    }

    @Basic
    @Column(name = "edu_level_id", nullable = false)
    public Integer getEduLevelId() {
        return eduLevelId;
    }

    public void setEduLevelId(Integer eduLevelId) {
        this.eduLevelId = eduLevelId;
    }

    @Basic
    @Column(name = "company_id", nullable = false)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "city_id", nullable = false)
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobEntity that = (JobEntity) o;
        return id.equals(that.id) &&
                jobNameId.equals(that.jobNameId) &&
                workingExpId.equals(that.workingExpId) &&
                eduLevelId.equals(that.eduLevelId) &&
                companyId.equals(that.companyId) &&
                cityId.equals(that.cityId) &&
                Objects.equals(jobNumber, that.jobNumber) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(emplType, that.emplType) &&
                Objects.equals(recruitCount, that.recruitCount) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, jobNumber, jobNameId, salary, emplType, recruitCount, workingExpId, eduLevelId, companyId, cityId, createDate, endDate);
    }
}

package indi.aiurmaple.recruitanalyze.datatransform.entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "job", schema = "recruit_analyze")
public class JobEntity {
    private long id;
    private String jobNumber;
    private int jobNameId;
    private String salary;
    private String emplType;
    private Byte recruitCount;
    private int workingExpId;
    private int eduLevelId;
    private long companyId;
    private int cityId;
    private Timestamp createDate;
    private Timestamp endDate;


    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "job_number")
    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Basic
    @Column(name = "job_name_id")
    public int getJobNameId() {
        return jobNameId;
    }

    public void setJobNameId(int jobNameId) {
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
    public Byte getRecruitCount() {
        return recruitCount;
    }

    public void setRecruitCount(Byte recruitCount) {
        this.recruitCount = recruitCount;
    }

    @Basic
    @Column(name = "working_exp_id")
    public int getWorkingExpId() {
        return workingExpId;
    }

    public void setWorkingExpId(int workingExpId) {
        this.workingExpId = workingExpId;
    }

    @Basic
    @Column(name = "edu_level_id")
    public int getEduLevelId() {
        return eduLevelId;
    }

    public void setEduLevelId(int eduLevelId) {
        this.eduLevelId = eduLevelId;
    }

    @Basic
    @Column(name = "company_id")
    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "city_id")
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
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
        return id == that.id &&
                jobNameId == that.jobNameId &&
                workingExpId == that.workingExpId &&
                eduLevelId == that.eduLevelId &&
                companyId == that.companyId &&
                cityId == that.cityId &&
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

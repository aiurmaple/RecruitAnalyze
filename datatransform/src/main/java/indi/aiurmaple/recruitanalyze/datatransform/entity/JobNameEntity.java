package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "job_name", schema = "recruit_analyze")
public class JobNameEntity {
    private Integer id;
    private String jobLabel;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "job_label")
    public String getJobLabel() {
        return jobLabel;
    }

    public void setJobLabel(String jobLabel) {
        this.jobLabel = jobLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobNameEntity that = (JobNameEntity) o;
        return id.equals(that.id) &&
                Objects.equals(jobLabel, that.jobLabel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, jobLabel);
    }
}

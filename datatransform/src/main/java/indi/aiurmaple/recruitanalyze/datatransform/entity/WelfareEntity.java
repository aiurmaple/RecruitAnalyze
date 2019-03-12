package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "welfare", schema = "recruit_analyze")
public class WelfareEntity {
    private Integer id;
    private String welfareLabel;
    private Set<JobEntity> jobs = new HashSet<>();

    public WelfareEntity() {

    }

    public WelfareEntity(Integer id, String welfareLabel) {
        this.id = id;
        this.welfareLabel = welfareLabel;
    }

    public WelfareEntity(String welfareLabel) {
        this.welfareLabel = welfareLabel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "welfare_label")
    public String getWelfareLabel() {
        return welfareLabel;
    }

    public void setWelfareLabel(String welfareLabel) {
        this.welfareLabel = welfareLabel;
    }

    @ManyToMany(mappedBy = "welfares")
    public Set<JobEntity> getJobs() {
        return jobs;
    }

    public void setJobs(Set<JobEntity> jobs) {
        this.jobs = jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WelfareEntity that = (WelfareEntity) o;
        return id.equals(that.id) &&
                Objects.equals(welfareLabel, that.welfareLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, welfareLabel);
    }
}

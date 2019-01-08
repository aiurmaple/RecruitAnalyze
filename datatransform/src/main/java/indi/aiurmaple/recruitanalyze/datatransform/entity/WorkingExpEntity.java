package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "working_exp", schema = "recruit_analyze")
public class WorkingExpEntity {
    private Integer id;
    private String workingLabel;

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
    @Column(name = "working_label")
    public String getWorkingLabel() {
        return workingLabel;
    }

    public void setWorkingLabel(String workingLabel) {
        this.workingLabel = workingLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingExpEntity that = (WorkingExpEntity) o;
        return id.equals(that.id) &&
                Objects.equals(workingLabel, that.workingLabel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, workingLabel);
    }
}

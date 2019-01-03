package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "edu_level", schema = "recruit_analyze")
public class EduLevelEntity {
    private int id;
    private String eduLabel;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "edu_label")
    public String getEduLabel() {
        return eduLabel;
    }

    public void setEduLabel(String eduLabel) {
        this.eduLabel = eduLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EduLevelEntity that = (EduLevelEntity) o;
        return id == that.id &&
                Objects.equals(eduLabel, that.eduLabel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, eduLabel);
    }
}

package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "welfare", schema = "recruit_analyze")
public class WelfareEntity {
    private Integer id;
    private String welfareLabel;

    @Id
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

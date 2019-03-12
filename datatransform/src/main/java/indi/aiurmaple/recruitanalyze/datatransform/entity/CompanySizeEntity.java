package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "company_size", schema = "recruit_analyze")
public class CompanySizeEntity {
    private Integer id;
    private String sizeLabel;

    public CompanySizeEntity() {

    }

    public CompanySizeEntity(String sizeLabel) {
        this.sizeLabel = sizeLabel;
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
    @Column(name = "size_label")
    public String getSizeLabel() {
        return sizeLabel;
    }

    public void setSizeLabel(String sizeLabel) {
        this.sizeLabel = sizeLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySizeEntity that = (CompanySizeEntity) o;
        return id.equals(that.id) &&
                Objects.equals(sizeLabel, that.sizeLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sizeLabel);
    }
}

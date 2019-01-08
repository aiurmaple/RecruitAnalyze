package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "company_type", schema = "recruit_analyze")
public class CompanyTypeEntity {
    private Integer id;
    private String typeLabel;

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
    @Column(name = "type_label")
    public String getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyTypeEntity that = (CompanyTypeEntity) o;
        return id.equals(that.id) &&
                Objects.equals(typeLabel, that.typeLabel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, typeLabel);
    }
}

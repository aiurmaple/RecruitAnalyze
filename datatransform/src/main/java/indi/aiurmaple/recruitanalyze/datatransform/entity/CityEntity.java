package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "city", schema = "recruit_analyze")
public class CityEntity {
    private Integer id;
    private String cityLabel;

    public CityEntity() {

    }

    public CityEntity(String cityLabel) {
        this.cityLabel = cityLabel;
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
    @Column(name = "city_label")
    public String getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(String cityLabel) {
        this.cityLabel = cityLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity that = (CityEntity) o;
        return id.equals(that.id) &&
                Objects.equals(cityLabel, that.cityLabel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, cityLabel);
    }
}

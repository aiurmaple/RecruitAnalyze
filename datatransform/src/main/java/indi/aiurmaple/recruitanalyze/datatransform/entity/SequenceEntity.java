package indi.aiurmaple.recruitanalyze.datatransform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sequence", schema = "recruit_analyze")
public class SequenceEntity {
    private Integer id;
    private String tableName;
    private Long nextId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "next_id")
    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SequenceEntity that = (SequenceEntity) o;
        return id.equals(that.id) &&
                nextId.equals(that.nextId) &&
                Objects.equals(tableName, that.tableName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, tableName, nextId);
    }
}

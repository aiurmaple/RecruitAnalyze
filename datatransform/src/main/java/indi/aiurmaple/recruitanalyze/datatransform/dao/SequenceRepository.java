package indi.aiurmaple.recruitanalyze.datatransform.dao;

import indi.aiurmaple.recruitanalyze.datatransform.entity.SequenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 13925 on 2019/3/5.
 */
public interface SequenceRepository extends JpaRepository<SequenceEntity, Integer> {
    SequenceEntity findByTableName(String tableName);
}

package indi.aiurmaple.recruitanalyze.datatransform.dao;

import indi.aiurmaple.recruitanalyze.datatransform.entity.EduLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 13925 on 2019/3/8.
 */
public interface EduLevelRepository extends JpaRepository<EduLevelEntity,Integer> {
    EduLevelEntity findByEduLabel(String eduLevel);
}

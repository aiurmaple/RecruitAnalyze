package indi.aiurmaple.recruitanalyze.datatransform.dao;

import indi.aiurmaple.recruitanalyze.datatransform.entity.WelfareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 13925 on 2019/3/6.
 */
public interface WelfareRepository extends JpaRepository<WelfareEntity,Integer> {
    WelfareEntity findByWelfareLabel(String welfareLabel);
}

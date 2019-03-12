package indi.aiurmaple.recruitanalyze.datatransform.dao;

import indi.aiurmaple.recruitanalyze.datatransform.entity.CompanySizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 13925 on 2019/3/8.
 */
public interface CompanySizeRepository extends JpaRepository<CompanySizeEntity, Integer> {
    CompanySizeEntity findBySizeLabel(String sizeLabel);
}

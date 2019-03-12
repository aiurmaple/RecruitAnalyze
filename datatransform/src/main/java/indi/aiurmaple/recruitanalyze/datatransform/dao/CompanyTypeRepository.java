package indi.aiurmaple.recruitanalyze.datatransform.dao;

import indi.aiurmaple.recruitanalyze.datatransform.entity.CompanyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 13925 on 2019/3/8.
 */
public interface CompanyTypeRepository extends JpaRepository<CompanyTypeEntity, Integer> {
    CompanyTypeEntity findByTypeLabel(String typeLabel);
}

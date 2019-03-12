package indi.aiurmaple.recruitanalyze.datatransform.dao;

import indi.aiurmaple.recruitanalyze.datatransform.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 13925 on 2019/3/6.
 */
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findByCompanyNumber(String companyNumber);
}

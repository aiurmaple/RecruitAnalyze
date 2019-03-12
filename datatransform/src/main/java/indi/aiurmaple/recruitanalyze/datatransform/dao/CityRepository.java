package indi.aiurmaple.recruitanalyze.datatransform.dao;

import indi.aiurmaple.recruitanalyze.datatransform.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 13925 on 2019/3/8.
 */
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
    CityEntity findByCityLabel(String cityLabel);
}

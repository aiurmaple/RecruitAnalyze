package indi.aiurmaple.recruitanalyze.datatransform.dao;

import indi.aiurmaple.recruitanalyze.datatransform.entity.WorkingExpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 13925 on 2019/3/8.
 */
public interface WorkingExpRepository extends JpaRepository<WorkingExpEntity, Integer> {
    WorkingExpEntity findByWorkingLabel(String workingLabel);
}

package indi.aiurmaple.recruitanalyze.datatransform.dao;

import indi.aiurmaple.recruitanalyze.datatransform.entity.JobNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 13925 on 2019/3/8.
 */
public interface JobNameRepository extends JpaRepository<JobNameEntity,Integer> {
    JobNameEntity findByJobLabel(String jobLabel);
}

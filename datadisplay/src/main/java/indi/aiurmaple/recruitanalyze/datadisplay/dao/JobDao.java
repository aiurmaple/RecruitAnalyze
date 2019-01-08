package indi.aiurmaple.recruitanalyze.datadisplay.dao;

import indi.aiurmaple.recruitanalyze.datadisplay.entity.JobEntity;

import java.util.List;

public interface JobDao {
    List<JobEntity> getAll();
}

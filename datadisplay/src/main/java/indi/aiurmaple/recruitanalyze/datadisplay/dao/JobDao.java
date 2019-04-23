package indi.aiurmaple.recruitanalyze.datadisplay.dao;

import indi.aiurmaple.recruitanalyze.datadisplay.model.JobCityModel;
import indi.aiurmaple.recruitanalyze.datadisplay.model.JobExpModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobDao {
    Integer getJobsNum(@Param("time") Boolean time, @Param("date") String date);

    List<Integer> getJobsNumByCity(@Param("cityIds") Integer[] cityIds, @Param("date") String date);

    List<Integer> getJobsNumByEdu(@Param("jobNameId") Integer jobNameId, @Param("eduLevelIds") Integer[] eduLevelIds,
                                  @Param("date") String date);

    List<JobCityModel> getJobsSalaryByCity(@Param("jobNameId") Integer jobNameId, @Param("cityIds") Integer[] cityIds,
                                           @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<JobExpModel> getJobsSalaryByExp(@Param("jobNameId") Integer jobNameId,
                                         @Param("workingExpIds") Integer[] workingExpIds,
                                         @Param("date") String date);

    List<Integer> getJobsNumByQuarter(@Param("jobNameId") Integer jobNameId, @Param("startDate") String startDate,
                                      @Param("times") String[] times);
}

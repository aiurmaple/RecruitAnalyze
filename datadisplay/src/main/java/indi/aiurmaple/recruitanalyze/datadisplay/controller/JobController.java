package indi.aiurmaple.recruitanalyze.datadisplay.controller;

import indi.aiurmaple.recruitanalyze.datadisplay.entity.JobNameEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.service.JobNameService;
import indi.aiurmaple.recruitanalyze.datadisplay.service.JobService;
import indi.aiurmaple.recruitanalyze.datadisplay.util.Quarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @Autowired
    private JobNameService jobNameService;

    @RequestMapping(value = "/num", method = RequestMethod.GET)
    public ResponseModel<Integer> getJobsNum(Boolean time) {
        if (time != null) {
            Integer num = jobService.getJobsNum(time);
            return new ResponseModel<>(HttpServletResponse.SC_OK, true,
                    "Success!", num);
        }

        return new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                "Input parameter error, at least one parameter !!", null);
    }

    @RequestMapping(value = "/num/city", method = RequestMethod.GET)
    public ResponseModel<List<Integer>> getJobsNum(Integer[] cityIds) {
        if (cityIds != null && cityIds.length != 0) {
            List<Integer> numList = jobService.getJobsNumByCity(cityIds);
            return new ResponseModel<>(HttpServletResponse.SC_OK, true,
                    "Success!", numList);
        }

        return new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                "Input parameter error, at least one parameter !!", null);
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public ResponseModel<List<JobNameEntity>> getJobsName() {
        List<JobNameEntity> jobNameEntities = jobNameService.getAll();
        return new ResponseModel<>(HttpServletResponse.SC_OK, true,
                "Success!", jobNameEntities);
    }

    @RequestMapping(value = "/salary/city", method = RequestMethod.GET)
    public ResponseModel<Map<String, List<Integer>>> getJobsSalaryByCity(Integer jobNameId, Integer[] cityIds, Quarter[] quarters) {
        if (jobNameId == null || cityIds == null || cityIds.length == 0 || quarters == null) {
            return new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                    "Input parameter error, Please check your parameter!", null);
        }
        Map<String, List<Integer>> salaryMap = jobService.getJobsSalaryByCity(jobNameId, cityIds, quarters);
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", salaryMap);
    }

    @RequestMapping(value = "/salary/experience", method = RequestMethod.GET)
    public ResponseModel<List<Integer>> getJobsSalaryByExp(Integer jobNameId, Integer[] workingExpIds) {
        if (jobNameId == null || workingExpIds == null || workingExpIds.length == 0) {
            return new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                    "Input parameter error, Please check your parameter!", null);
        }
        List<Integer> salaryList = jobService.getJobsSalaryByExp(jobNameId, workingExpIds);
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", salaryList);
    }

    @RequestMapping(value = "/num/edu", method = RequestMethod.GET)
    public ResponseModel<List<Integer>> getJobsNumByEdu(Integer jobNameId, Integer[] eduLevelIds) {
        if (jobNameId == null || eduLevelIds == null || eduLevelIds.length == 0) {
            return new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                    "Input parameter error, Please check your parameter!", null);
        }
        List<Integer> nums = jobService.getJobsNumByEdu(jobNameId, eduLevelIds);
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", nums);
    }

    @RequestMapping(value = "/quarter/num", method = RequestMethod.GET)
    public ResponseModel<List<Integer>> getJobsNumByQuarter(Integer jobNameId) {
        if (jobNameId == null) {
            return new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                    "Input parameter error, Please check your parameter!", null);
        }
        List<Integer> nums = jobService.getJobsNumByQuarter(jobNameId);
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", nums);
    }
}

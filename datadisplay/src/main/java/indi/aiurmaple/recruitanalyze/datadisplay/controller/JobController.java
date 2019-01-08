package indi.aiurmaple.recruitanalyze.datadisplay.controller;

import indi.aiurmaple.recruitanalyze.datadisplay.entity.JobEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(method = RequestMethod.GET)
    public List<JobEntity> getAll() {
        return jobService.getAll();
    }
}

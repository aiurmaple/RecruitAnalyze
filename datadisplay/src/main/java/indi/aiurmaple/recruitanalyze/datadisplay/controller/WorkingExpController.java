package indi.aiurmaple.recruitanalyze.datadisplay.controller;

import indi.aiurmaple.recruitanalyze.datadisplay.entity.WorkingExpEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.service.WorkingExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/experience")
public class WorkingExpController {
    @Autowired
    private WorkingExpService workingExpService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseModel<List<WorkingExpEntity>> getAll() {
        List<WorkingExpEntity> experience = workingExpService.getAll();
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", experience);
    }
}

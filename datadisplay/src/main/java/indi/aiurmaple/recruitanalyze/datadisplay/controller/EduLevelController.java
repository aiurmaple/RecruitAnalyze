package indi.aiurmaple.recruitanalyze.datadisplay.controller;

import indi.aiurmaple.recruitanalyze.datadisplay.entity.EduLevelEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.service.EduLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/edus")
public class EduLevelController {
    @Autowired
    private EduLevelService eduLevelService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseModel<List<EduLevelEntity>> getAll() {
        List<EduLevelEntity> eduLevelEntities = eduLevelService.getAll();
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", eduLevelEntities);
    }
}

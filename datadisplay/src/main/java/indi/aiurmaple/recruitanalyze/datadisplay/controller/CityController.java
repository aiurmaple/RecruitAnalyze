package indi.aiurmaple.recruitanalyze.datadisplay.controller;

import indi.aiurmaple.recruitanalyze.datadisplay.entity.CityEntity;
import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/citys")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseModel<List<CityEntity>> getAll() {
        List<CityEntity> citys = cityService.getAll();
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", citys);
    }

}

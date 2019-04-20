package indi.aiurmaple.recruitanalyze.datadisplay.controller;

import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.model.WelfareReponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.service.WelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/welfare")
public class WelfareController {
    @Autowired
    private WelfareService welfareService;

    @RequestMapping(value = "/num", method = RequestMethod.GET)
    public ResponseModel<List<WelfareReponseModel>> getWelfareNumByJob(Integer jobNameId) {
        if(jobNameId == null) {
            return new ResponseModel<>(HttpServletResponse.SC_BAD_REQUEST, false,
                    "Input parameter error, Please check your parameter!", null);
        }
        List<WelfareReponseModel> welfareReponseModels = welfareService.getWelfareNumByJob(jobNameId);
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", welfareReponseModels);
    }
}

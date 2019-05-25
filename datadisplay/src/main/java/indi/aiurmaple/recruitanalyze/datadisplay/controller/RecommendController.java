package indi.aiurmaple.recruitanalyze.datadisplay.controller;

import indi.aiurmaple.recruitanalyze.datadisplay.model.ResponseModel;
import indi.aiurmaple.recruitanalyze.datadisplay.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    @GetMapping("/salary")
    public ResponseModel<List<Integer>> getSalaryRanking() {
        List<Integer> list = recommendService.getSalaryRanking();
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", list);
    }

    @GetMapping("/edu")
    public ResponseModel<List<Integer>> getEduLevelRanking() {
        List<Integer> list = recommendService.getEduLevelRanking();
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", list);
    }

    @GetMapping("/job")
    public ResponseModel<List<Integer>> getJobRanking() {
        List<Integer> list = recommendService.getJobRanking();
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", list);
    }

    @GetMapping("/city")
    public ResponseModel<List<Integer>> getJobRankingByCity() {
        List<Integer> list = recommendService.getJobRankingByCity();
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", list);
    }

    @GetMapping("/working")
    public ResponseModel<List<Integer>> getJobRankingByWorkingExp() {
        List<Integer> list = recommendService.getJobRankingByWorkingExp();
        return new ResponseModel<>(HttpServletResponse.SC_OK, true, "Success!", list);
    }
}

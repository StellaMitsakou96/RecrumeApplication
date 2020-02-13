package gr.codehub.api.controller.reportingServiceController;

import gr.codehub.api.service.reportingService.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    ReportingService reportingService;

    @GetMapping("mostRequestedAndOfferedSkills")
    public List<String> getMostFamousSkills(){
        return reportingService.findMostFrequent();
    }

//    @GetMapping("notMatchedSkillsByTheApplicants")
//    public List<Skill> getNoMatchedSkills(){
//        List<Skill> skills = reportingService.getSkills();
//        return skills;
//    }
}
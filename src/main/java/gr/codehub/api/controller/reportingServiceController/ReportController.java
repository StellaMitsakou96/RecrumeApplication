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

    /**
     * find the five most famous skills
     *
     * @return a list with name of skill and each how many times it appears in job offer's list
     */
    @GetMapping("mostRequestedAndOfferedSkills")
    public List<String> getMostFamousSkills() {
        return reportingService.findMostFrequent();
    }

}

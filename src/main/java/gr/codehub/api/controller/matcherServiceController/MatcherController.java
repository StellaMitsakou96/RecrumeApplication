package gr.codehub.api.controller.matcherServiceController;

import gr.codehub.api.dto.MatcherDTO;
import gr.codehub.api.model.Matcher;
import gr.codehub.api.service.matcherService.MatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatcherController {
    @Autowired
    private MatcherService matcherService;

    /**
     * create manual match of an applicant and job offer in DB
     * @param matcherDTO1
     * @return the match
     */
    @PostMapping("manualMatcher")   //Here is manual matching
    public Matcher getMatch(@RequestBody MatcherDTO matcherDTO1) {
        return matcherService.getManualMatch(matcherDTO1);
    }

    /**
     * update a manual match
     * @param id
     * @param matcherDTO
     * @return the updated match
     */
    @PutMapping("manualMatch/update/{id}")
    public Matcher updateOne(@PathVariable int id, @RequestBody MatcherDTO matcherDTO) {
        return matcherService.updateOne(id, matcherDTO);
    }

    /**
     * finalize a manual match - soft delete of the match
     * @param id
     * @return a String message
     */
    @PutMapping("manualMatch/finalize/{id}")
    public String softDelete(@PathVariable int id) {
        return matcherService.finalizedMatch(id);
    }

    /**
     * create an automated match by a given applicant_id
     * @param matcherDTO1
     * @return the automated match
     */
    @PostMapping("automatedMatcher")
    public Matcher getAllPairsMatched(@RequestBody MatcherDTO matcherDTO1) {
        return matcherService.getAutomatedMatch(matcherDTO1);
    }

    /**
     * soft delete of an automated match
     * @param id
     * @return a String message
     */
    @PutMapping("automated/Matcherfinalize/{id}")
    public String softDeleteForAutomatedMatch(@PathVariable int id) {
        return matcherService.finalizedAutomatedMatch(id);
    }

}

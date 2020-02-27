package gr.codehub.api.controller.ajsServiceContollers.Skills;

import gr.codehub.api.dto.SkillDTO;
import gr.codehub.api.dto.SkillSetDTO;
import gr.codehub.api.dto.SkillSetForJobOfferDTO;
import gr.codehub.api.dto.SkillTwoDTO;
import gr.codehub.api.exception.skillException.SkillIdNotFoundException;
import gr.codehub.api.model.Skill;
import gr.codehub.api.model.SkillSet;
import gr.codehub.api.model.SkillSetForJobOffer;
import gr.codehub.api.service.ajsService.skillService.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class SkillController {
    @Autowired
    private SkillService skillService;

    /**
     * @return all skills
     */
    @GetMapping("skill")
    public List<Skill> getAllSkills() {
        return skillService.getAllSkillsFromDB();
    }

    /**
     * search all skills by id
     *
     * @param id of the skill
     * @return the skill by the given id
     * @throws SkillIdNotFoundException
     */
    @GetMapping("skill/{id}")
    public Skill getSkill(@PathVariable int id) throws SkillIdNotFoundException {
        return skillService.getSkillByIdFromDB(id);
    }

    /**
     * @return the skills from excel
     * @throws IOException
     */
    @GetMapping("skills/fromExcel")
    public List<Skill> getSkillFromExcel() throws IOException {
        return skillService.readFromExcel();
    }

    /**
     * request for adding a new skill
     *
     * @param skillDTO
     * @return the new skill
     */
    @PostMapping("newSkill")
    public Skill newSkill(@RequestBody SkillDTO skillDTO) {
        return skillService.save(skillDTO);
    }

    /**
     * merge two skills into one new
     *
     * @param skillTwoDTO
     * @return the new skill
     */
    @PostMapping("skill/merge")
    public Skill mergeTwoSkills(@RequestBody SkillTwoDTO skillTwoDTO) {
        return skillService.mergeSkills(skillTwoDTO);
    }

    /**
     * split one skill into two new skills
     *
     * @param skillDTO
     * @return two new skills
     */
    @PostMapping("skill/split")
    public Skill splitOneSkill(@RequestBody SkillDTO skillDTO) {
        return skillService.split(skillDTO);
    }

    /**
     * request for adding new skill for applicant
     *
     * @param skillSetDTO
     * @return the new skill
     * @throws Exception
     */
    @PostMapping("skillset")
    public SkillSet createSkillForApplicant(@RequestBody SkillSetDTO skillSetDTO) throws Exception {
        return skillService.createSkillSet(skillSetDTO);
    }

    /**
     * request for adding new skill for job offer
     *
     * @param skillSetForJobOfferDTO
     * @return the new skill
     * @throws Exception
     */
    @PostMapping("skillsetforjoboffer")
    public SkillSetForJobOffer createSkillForJobOffer(@RequestBody SkillSetForJobOfferDTO skillSetForJobOfferDTO) throws Exception {
        return skillService.createSkillSetForJobOffer(skillSetForJobOfferDTO);
    }

    /**
     * request for updating skill by id
     *
     * @param id       skill id
     * @param skillDTO
     * @return the updated skill
     */
    @PutMapping("skill/{id}")
    public Skill updateOne(@PathVariable int id, @RequestBody SkillDTO skillDTO) {
        return skillService.updateOne(id, skillDTO);
    }

    /**
     * request for soft delete by skill id
     *
     * @param id skill id
     * @return "ok" if soft delete implemented
     */
    @PutMapping("skill/inactive/{id}")
    public String softDelete(@PathVariable int id) {
        return skillService.softDelete(id);
    }
}

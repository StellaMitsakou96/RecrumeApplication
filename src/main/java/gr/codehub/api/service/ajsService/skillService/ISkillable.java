package gr.codehub.api.service.ajsService.skillService;

import gr.codehub.api.dto.SkillDTO;
import gr.codehub.api.dto.SkillSetDTO;
import gr.codehub.api.dto.SkillSetForJobOfferDTO;
import gr.codehub.api.dto.SkillTwoDTO;
import gr.codehub.api.exception.skillException.SkillIdNotFoundException;
import gr.codehub.api.model.Skill;
import gr.codehub.api.model.SkillSet;
import gr.codehub.api.model.SkillSetForJobOffer;

import java.io.IOException;
import java.util.List;

public interface ISkillable {
    List<Skill> getAllSkillsFromDB();

    Skill getSkillByIdFromDB(int id) throws SkillIdNotFoundException;

    Skill save(SkillDTO skillDTO);

    SkillSet createSkillSet(SkillSetDTO skillSetDTO);

    SkillSetForJobOffer createSkillSetForJobOffer(SkillSetForJobOfferDTO skillSetForJobOfferDTO);

    Skill updateOne(int id, SkillDTO skillDTO);

    String softDelete(int id);

    List<Skill> readFromExcel() throws IOException;

    Skill mergeSkills(SkillTwoDTO skillTwoDTO);

    Skill split(SkillDTO skillDTO);

    Skill splitToDash(String nameSkill);

    Skill splitToUnderScore(String nameSkill);

    Skill splitStoSpace(String nameSkill);
}

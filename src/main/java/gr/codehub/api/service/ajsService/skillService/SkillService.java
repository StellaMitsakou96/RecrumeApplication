package gr.codehub.api.service.ajsService.skillService;

import gr.codehub.api.dto.SkillDTO;
import gr.codehub.api.dto.SkillSetDTO;
import gr.codehub.api.dto.SkillSetForJobOfferDTO;
import gr.codehub.api.dto.SkillTwoDTO;
import gr.codehub.api.exception.skillException.SkillIdNotFoundException;
import gr.codehub.api.model.Skill;
import gr.codehub.api.model.SkillSet;
import gr.codehub.api.model.SkillSetForJobOffer;
import gr.codehub.api.repositories.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class SkillService implements ISkillable {
    private ApplicantRepository applicantsRepo;

    private CompanyRepository companiesRepo;

    private JobOfferRepository jobOffersRepo;

    private SkillRepository skillRepo;

    private SkillSetRepository skillSetsRepo;

    private SkillSetForJobOfferRepository skillSetForJobOffersRepo;

    @Autowired
    public SkillService(ApplicantRepository applicantsRepo, CompanyRepository companiesRepo, JobOfferRepository jobOffersRepo, SkillRepository skillRepo, SkillSetRepository skillSetsRepo, SkillSetForJobOfferRepository skillSetForJobOffersRepo) {
        this.applicantsRepo = applicantsRepo;
        this.companiesRepo = companiesRepo;
        this.jobOffersRepo = jobOffersRepo;
        this.skillRepo = skillRepo;
        this.skillSetsRepo = skillSetsRepo;
        this.skillSetForJobOffersRepo = skillSetForJobOffersRepo;
    }

    /**
     * @return from data base all skills
     */
    public List<Skill> getAllSkillsFromDB() {
        return skillRepo.findAll();
    }

    /**
     * @param id skill id
     * @return from data base skill by id
     * @throws SkillIdNotFoundException
     */
    public Skill getSkillByIdFromDB(int id) throws SkillIdNotFoundException {

        Skill skill = skillRepo.findById(id);
        if (skill == null) {
            throw new SkillIdNotFoundException("Skill = " + skill);
        }
        return skillRepo.findById(id);
    }

    /**
     * request for adding new skill
     *
     * @param skillDTO
     * @return the new skill and save in data base
     */
    public Skill save(SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setSkillName(skillDTO.getSkillName());
        return skillRepo.save(skill);
    }

    /**
     * request for adding new skill for applicant
     *
     * @param skillSetDTO
     * @return the new skill and save in database
     */
    public SkillSet createSkillSet(SkillSetDTO skillSetDTO) {
        SkillSet skillSet = new SkillSet();
        skillSet.setApplicant(applicantsRepo.findFirstById(skillSetDTO.getApplicant_id()));
        skillSet.setSkill(skillRepo.findFirstById(skillSetDTO.getSkill_id()));
        return skillSetsRepo.save(skillSet);

    }

    /**
     * request for adding new skill for job offer
     *
     * @param skillSetForJobOfferDTO
     * @return the new skill and save in data base
     */
    public SkillSetForJobOffer createSkillSetForJobOffer(SkillSetForJobOfferDTO skillSetForJobOfferDTO) {
        SkillSetForJobOffer skillSetForJobOffer = new SkillSetForJobOffer();
        skillSetForJobOffer.setJobOffer(jobOffersRepo.findFirstById(skillSetForJobOfferDTO.getJob_offer_id()));
        skillSetForJobOffer.setSkill(skillRepo.findFirstById(skillSetForJobOfferDTO.getSkill_id()));
        return skillSetForJobOffersRepo.save(skillSetForJobOffer);
    }

    /**
     * request for updating skill
     *
     * @param id
     * @param skillDTO
     * @return the updated skill and save in data base
     */
    public Skill updateOne(int id, SkillDTO skillDTO) {
        Skill skill = skillRepo.findById(id);
        if (skillDTO.getSkillName() != null) skill.setSkillName(skillDTO.getSkillName());
        return skillRepo.save(skill);
    }

    /**
     * update the active status false
     * in order to implement soft delete of a skill
     *
     * @param id skill id
     * @return ok if the implementation fulfilled
     */
    public String softDelete(int id) {
        Skill skill = skillRepo.findById(id);
        skill.setActive(false);
        skillRepo.save(skill);
        return "ok";
    }

    /**
     * reads the skills from excel file
     *
     * @return all skills
     * @throws IOException
     */
    public List<Skill> readFromExcel() throws IOException {
        String excelFileName = "data for recrume,.xlsx";
        File file = ResourceUtils.getFile("classpath:" + excelFileName);
        FileInputStream excelFile = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(2);
        Iterator<Row> row = datatypeSheet.iterator();
        row.next(); //reads the headers
        while (row.hasNext()) {
            Row currentRow = row.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            Cell nameCell = cellIterator.next();
            Skill skill = new Skill(nameCell.getStringCellValue());
            skill.setActive(true);
            skillRepo.save(skill);
        }
        return skillRepo.findAll();
    }

    /**
     * merge two skills into new one
     *
     * @param skillTwoDTO
     * @return new skill
     */
    public Skill mergeSkills(SkillTwoDTO skillTwoDTO) {
        Skill skill = new Skill();
        skill.setSkillName(skillRepo.findBySkillName(skillTwoDTO.getSkillDTO1().getSkillName()).getSkillName() + " " + skillRepo.findBySkillName(skillTwoDTO.getSkillDTO2().getSkillName()).getSkillName());
        return skillRepo.save(skill);
    }

    /**
     * splits one skill in two new skills
     *
     * @param skillDTO
     * @return two new skills
     */
    public Skill split(SkillDTO skillDTO) {
        String nameSkill = skillDTO.getSkillName();
        Skill skill0 = new Skill();
        if (nameSkill.contains(" ")) {
            skill0 = splitStoSpace(nameSkill);
            String[] splitStr = nameSkill.split("\\s+");
            Skill skill2 = new Skill();
            skill2.setSkillName(splitStr[1]);
            return skillRepo.save(skill2);
        } else if (nameSkill.contains("_")) {
            skill0 = splitToUnderScore(nameSkill);
            String[] splitStr = nameSkill.split("\\_");
            Skill skill2 = new Skill();
            skill2.setSkillName(splitStr[1]);
            return skillRepo.save(skill2);
        } else if (nameSkill.contains("-")) {
            skill0 = splitToDash(nameSkill);
            String[] splitStr = nameSkill.split("\\-");
            Skill skill2 = new Skill();
            skill2.setSkillName(splitStr[1]);
            return skillRepo.save(skill2);
        }
        return null;
    }

    /**
     * function called in split() method
     *
     * @param nameSkill
     * @return the first skill, before symbol (-), in data base
     */
    public Skill splitToDash(String nameSkill) {
        String[] splitStr = nameSkill.split("\\-");
        Skill skill1 = new Skill();
        skill1.setSkillName(splitStr[0]);
        return skillRepo.save(skill1);
    }

    /**
     * function called in split() method
     *
     * @param nameSkill
     * @return the first skill, before symbol (_), in data base
     */
    public Skill splitToUnderScore(String nameSkill) {
        String[] splitStr = nameSkill.split("\\_");
        Skill skill1 = new Skill();
        skill1.setSkillName(splitStr[0]);
        return skillRepo.save(skill1);
    }

    /**
     * function called in split() method
     *
     * @param nameSkill
     * @return the first skill, before symbol space, in data base
     */
    public Skill splitStoSpace(String nameSkill) {
        String[] splitStr = nameSkill.split("\\s+");
        Skill skill1 = new Skill();
        skill1.setSkillName(splitStr[0]);
        return skillRepo.save(skill1);
    }
}


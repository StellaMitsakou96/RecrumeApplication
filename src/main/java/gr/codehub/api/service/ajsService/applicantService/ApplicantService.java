package gr.codehub.api.service.ajsService.applicantService;

import gr.codehub.api.dto.ApplicantDTO;
import gr.codehub.api.exception.applicantException.ApplicantIdNotFoundException;
import gr.codehub.api.exception.applicantException.ApplicantLastNameNotFoundException;
import gr.codehub.api.exception.applicantException.ApplicantRegionNotFoundException;
import gr.codehub.api.model.Applicant;
import gr.codehub.api.model.Skill;
import gr.codehub.api.model.SkillSet;
import gr.codehub.api.model.enumarator.LevelEnumarator;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicantService implements IApplicantable {
    private ApplicantRepository applicantsRepo;

    private CompanyRepository companiesRepo;

    private JobOfferRepository jobOffersRepo;

    private SkillRepository skillRepo;

    private SkillSetRepository skillSetsRepo;

    private SkillSetForJobOfferRepository skillSetForJobOffersRepo;

    @Autowired
    public ApplicantService(ApplicantRepository applicantsRepo, CompanyRepository companiesRepo, JobOfferRepository jobOffersRepo, SkillRepository skillRepo, SkillSetRepository skillSetsRepo, SkillSetForJobOfferRepository skillSetForJobOffersRepo) {
        this.applicantsRepo = applicantsRepo;
        this.companiesRepo = companiesRepo;
        this.jobOffersRepo = jobOffersRepo;
        this.skillRepo = skillRepo;
        this.skillSetsRepo = skillSetsRepo;
        this.skillSetForJobOffersRepo = skillSetForJobOffersRepo;
    }

    /**
     * @return from data base all applicants
     */
    public List<Applicant> getAllApplicantsFromDB() {
        return applicantsRepo.findAll();
    }

    /**
     * get the applicant by the given id
     *
     * @param id applicant id
     * @return the applicant by the given id
     * @throws ApplicantIdNotFoundException if the applicant by the given id does not exist in database
     */
    public Applicant getApplicantFromDB(int id) throws ApplicantIdNotFoundException {

        Applicant applicant = applicantsRepo.findById(id);
        if (applicant == null) {
            throw new ApplicantIdNotFoundException("Applicant id = " + id);
        }
        return applicant;
    }

    /**
     * get the applicant by last name
     *
     * @param lastName applicant id
     * @return the applicant by the given last name
     * @throws ApplicantLastNameNotFoundException if the applicant by the given last name
     *                                            does not exist in database
     */
    public Applicant getApplicantByNameFromDB(String lastName) throws ApplicantLastNameNotFoundException {

        Applicant applicant = applicantsRepo.findByName(lastName);
        if (applicant == null) {
            throw new ApplicantLastNameNotFoundException("Applicant last Name = " + lastName);
        }
        return applicant;
    }

    /**
     * @param region
     * @return from data base all applicants by region
     */
    public Optional<List<Applicant>> getApplicantByRegionFromDB(String region) throws ApplicantRegionNotFoundException {
        Optional<List<Applicant>> applicants = applicantsRepo.findAllByRegion(region);
        if (!applicants.isPresent()) {
            throw new ApplicantRegionNotFoundException("Region= " + region);
        }
        return applicants;
    }

    /**
     * search applicants by skill
     *
     * @param skill applicant skill
     * @return applicants
     */
    public List<Applicant> getApplicantsBySkillFromDB(String skill) {
        Iterator<Applicant> applicantIterator = applicantsRepo.findAll().iterator();
        List<Applicant> requestedApplicants = new ArrayList<>();
        while (applicantIterator.hasNext()) {
            Applicant applicant = applicantIterator.next();
            for (SkillSet s : applicant.getSkillSets()) {
                if (s.getSkill().getSkillName().equals(skill)) {
                    requestedApplicants.add(applicant);
                    break;
                }
            }
        }
        return requestedApplicants;
    }

    /**
     * request for adding new applicant
     *
     * @param applicantDTO
     * @return the new applicant and save in data base
     */
    public Applicant save(ApplicantDTO applicantDTO) {
        Applicant applicant = new Applicant();
        applicant.setFirstName(applicantDTO.getFirstName());
        applicant.setLastName(applicantDTO.getLastName());
        applicant.setAddress(applicantDTO.getAddress());
        applicant.setRegion(applicantDTO.getRegion());
        applicant.setEducationLevel(applicantDTO.getEducationLevel());
        return applicantsRepo.save(applicant);
    }

    /**
     * reads applicants from excel file
     *
     * @return all applicants
     * @throws IOException
     */
    public List<Applicant> readApplicantFromExcel() throws IOException {
        String excelFileName = "data for recrume,.xlsx";
        File file = ResourceUtils.getFile("classpath:" + excelFileName);
        FileInputStream excelFile = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> row = datatypeSheet.iterator();
        row.next();
        while (row.hasNext()) {
            Row currentRow = row.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            Cell firstNameCell = cellIterator.next();
            Cell lastNameCell = cellIterator.next();
            Cell addressCell = cellIterator.next();
            Cell regionCell = cellIterator.next();
            Cell educationLevel = cellIterator.next();
            Cell levelCell = cellIterator.next();

            Applicant applicant = new Applicant();
            applicant.setFirstName(firstNameCell.getStringCellValue());
            applicant.setLastName(lastNameCell.getStringCellValue());
            applicant.setAddress(addressCell.getStringCellValue());
            applicant.setRegion(regionCell.getStringCellValue());
            applicant.setEducationLevel(educationLevel.getStringCellValue());
            if (levelCell.getStringCellValue().equals("Mid")) {
                applicant.setLevelEnumarator(LevelEnumarator.MID);
            } else if (levelCell.getStringCellValue().equals("Senior")) {
                applicant.setLevelEnumarator(LevelEnumarator.SENIOR);
            } else if (levelCell.getStringCellValue().equals("Junior")) {
                applicant.setLevelEnumarator(LevelEnumarator.JUNIOR);
            }
            applicant = applicantsRepo.save(applicant);

            while (cellIterator.hasNext()) {
                Cell skillNameCell = cellIterator.next();
                SkillSet skillSet = new SkillSet();
                Skill skill = skillRepo.findBySkillName(skillNameCell.getStringCellValue());
                if (skill == null) {
                    skill = new Skill();
                    skill.setSkillName(skillNameCell.getStringCellValue());
                    skill.setActive(true);
                    skillRepo.save(skill);
                }
                skillSet.setApplicant(applicant);
                skillSet.setSkill(skill);
                skillSetsRepo.save(skillSet);
            }
        }
        return applicantsRepo.findAll();
    }
}

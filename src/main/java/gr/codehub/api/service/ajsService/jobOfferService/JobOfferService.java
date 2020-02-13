package gr.codehub.api.service.ajsService.jobOfferService;

import gr.codehub.api.dto.JobOfferDTO;
import gr.codehub.api.exception.jobOfferException.JobOfferApplicantIdNotFoundException;
import gr.codehub.api.exception.jobOfferException.JobOfferIdNotFoundException;
import gr.codehub.api.exception.jobOfferException.JobOfferNameNotFoundException;
import gr.codehub.api.model.Company;
import gr.codehub.api.model.JobOffer;
import gr.codehub.api.model.Skill;
import gr.codehub.api.model.SkillSetForJobOffer;
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
public class JobOfferService implements IJobOfferable {
    private ApplicantRepository applicantsRepo;

    private CompanyRepository companiesRepo;

    private JobOfferRepository jobOffersRepo;

    private SkillRepository skillRepo;

    private SkillSetRepository skillSetsRepo;

    private SkillSetForJobOfferRepository skillSetForJobOffersRepo;

    @Autowired
    public JobOfferService(ApplicantRepository applicantsRepo, CompanyRepository companiesRepo, JobOfferRepository jobOffersRepo, SkillRepository skillRepo, SkillSetRepository skillSetsRepo, SkillSetForJobOfferRepository skillSetForJobOffersRepo) {
        this.applicantsRepo = applicantsRepo;
        this.companiesRepo = companiesRepo;
        this.jobOffersRepo = jobOffersRepo;
        this.skillRepo = skillRepo;
        this.skillSetsRepo = skillSetsRepo;
        this.skillSetForJobOffersRepo = skillSetForJobOffersRepo;
    }

    /**
     * @return from data base all job offers
     */
    public List<JobOffer> getJobOffers() {
        return jobOffersRepo.findAll();
    }

    /**
     * get the job offer by the given id
     *
     * @param id job offer id
     * @return the job offer by the given id
     * @throws JobOfferIdNotFoundException if the job offer by the given id does not exist in data base
     */
    public JobOffer getJobOfferById(int id) throws JobOfferIdNotFoundException {
       /* //Deuteros tropos.
        return jobOffersRepo.findFirstByCompanyId(id);
       *//* //Prwtos tropos.
        JobOffer jo = jobOffersRepo.findById(id).get();
        return jo;*/
        JobOffer jobOffer = jobOffersRepo.findFirstByCompanyId(id);
        if (jobOffer == null) {
            throw new JobOfferIdNotFoundException("Job Offer id= " + id);
        }
        return jobOffer;
    }

    /**
     * get the job offer by id
     *
     * @param name job offer name
     * @return the job offer
     * @throws JobOfferNameNotFoundException if the name of job offer does not exist
     */
    public List<JobOffer> getJobOfferByNameFromDB(String name) throws JobOfferNameNotFoundException {
       // return jobOffersRepo.findByName(name);
        List<JobOffer> jobOffer = jobOffersRepo.findByName(name);
        if (jobOffer.isEmpty()) {
            throw new JobOfferNameNotFoundException("Job offer name= " + name);
        }
        return jobOffer;
    }

    /**
     * search job offers by name
     *
     * @param skill skill name
     * @return the job offers
     */
    public List<JobOffer> getJobOffersBySkillFromDB(String skill) {
        //return  skillSetForJobOffers.findAllBySkillFromRecrume(skill);
        // return jobOffersRepo.findBySkill(skill);
        Iterator<JobOffer> jobOfferIterator = jobOffersRepo.findAll().iterator();
        List<JobOffer> requestedJobOffers = new ArrayList<>();
        while (jobOfferIterator.hasNext()) {
            JobOffer jobOffer = jobOfferIterator.next();
            for (SkillSetForJobOffer s : jobOffer.getSkillSetForJobOffers()) {
                if (s.getSkill().getSkillName().equals(skill)) {
                    System.out.println(s.getSkill().getSkillName() + skill);
                    requestedJobOffers.add(jobOffer);
                    break;
                }
            }
        }
        return requestedJobOffers;
    }

    /**
     * search and compare in the list of job offer according to  given company name, job offer's company name
     *
     * @param nameOfCompany
     * @param nameOfJob
     * @return the job offers from a specific company
     */
    public List<JobOffer> getJobOfferOfACompany(String nameOfCompany, String nameOfJob) {
        Iterator<JobOffer> jobOfferIterator = jobOffersRepo.findAll().iterator();
        List<JobOffer> requestedJobOffers = new ArrayList<>();
        while (jobOfferIterator.hasNext()) {
            JobOffer jobOffer = jobOfferIterator.next();
            if (jobOffer.getCompany().getCompanyName().equals(nameOfCompany) && jobOffer.getJobOfferName().equals(nameOfJob)) {
                requestedJobOffers.add(jobOffer);
            }
        }
        return requestedJobOffers;
    }


    /**
     * request for adding new job offer
     *
     * @param jobOfferDTO
     * @return the new job offer and save in data base
     */
    public JobOffer save(JobOfferDTO jobOfferDTO) {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setApplicant(applicantsRepo.findFirstById(jobOfferDTO.getApplicant_id()));
        jobOffer.setCompany(companiesRepo.findFirstById(jobOfferDTO.getCompany_id()));
        jobOffer.setJobOfferName(jobOfferDTO.getJobOfferName());
        return jobOffersRepo.save(jobOffer);
    }

    /**
     *
     * @param id
     * @return the offer of the given applicant
     * @throws JobOfferApplicantIdNotFoundException
     */
    public List<JobOffer> getJobOffersByApplicantID(int id) throws JobOfferApplicantIdNotFoundException {
        /*if (id == 0) {
            return jobOffersRepo.findByApplicantIdIsNull();
        } else {
            return jobOffersRepo.findByApplicantId(id);
        }*/
        List<JobOffer> jobOffer = jobOffersRepo.findByApplicantId(id);
        if(id == 0){
            return jobOffersRepo.findByApplicantIdIsNull();
        }
        if(jobOffer.isEmpty()){
            throw new JobOfferApplicantIdNotFoundException("Applicant id= " + id);
        }
        return jobOffersRepo.findByApplicantId(id);
        //return jobOffer;
    }

    /**
     * reads job offers from excel file
     *
     * @return all job offers
     * @throws IOException
     */
    public List<JobOffer> readJobOfferFromExcel() throws IOException {
        String excelFileName = "data for recrume,.xlsx";
        File file = ResourceUtils.getFile("classpath:" + excelFileName);
        FileInputStream excelFile = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(1);
        Iterator<Row> row = datatypeSheet.iterator();
        row.next();
        while (row.hasNext()) {
            Row currentRow = row.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            Cell companyNameCell = cellIterator.next();
            String companyName = companyNameCell.getStringCellValue();
            Optional<List<Company>> companies = companiesRepo.findAllByCompanyName(companyName);
            Company company;
            if (companies.isEmpty()) {
                company = new Company();
                company.setCompanyName(companyName);
                company = companiesRepo.save(company);
            }
            company = companiesRepo.findAllByCompanyName(companyName).get().get(0);
            Cell firstNameCell = cellIterator.next();
            JobOffer jobOffer = new JobOffer();
            jobOffer.setJobOfferName(firstNameCell.getStringCellValue());
            jobOffer.setCompany(company);
            jobOffer = jobOffersRepo.save(jobOffer);

            Cell regionNameCell = cellIterator.next();
            company.setRegion(regionNameCell.getStringCellValue());
            company = companiesRepo.save(company);

            Cell levelCell = cellIterator.next();
            if (levelCell.getStringCellValue().equals("Mid")) {
                jobOffer.setLevelEnumarator(LevelEnumarator.MID);
            } else if (levelCell.getStringCellValue().equals("Senior")) {
                jobOffer.setLevelEnumarator(LevelEnumarator.SENIOR);
            } else if (levelCell.getStringCellValue().equals("Junior")) {
                jobOffer.setLevelEnumarator(LevelEnumarator.JUNIOR);
            }
            else {
                jobOffer.setLevelEnumarator(null);
            }
            while (cellIterator.hasNext()) {
                Cell skillNameCell = cellIterator.next();
                System.out.println(skillNameCell.getStringCellValue());
                SkillSetForJobOffer skillSetForJobOffer = new SkillSetForJobOffer();
                Skill skill = skillRepo.findBySkillName(skillNameCell.getStringCellValue());
                if (skill == null) {
                    skill = new Skill();
                    skill.setSkillName(skillNameCell.getStringCellValue());
                    skill.setActive(true);
                    skillRepo.save(skill);
                }
                skillSetForJobOffer.setJobOffer(jobOffer);
                skillSetForJobOffer.setSkill(skill);

                skillSetForJobOffersRepo.save(skillSetForJobOffer);
            }
        }
        return jobOffersRepo.findAll();
    }
}

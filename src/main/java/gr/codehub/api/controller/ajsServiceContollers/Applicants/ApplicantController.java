package gr.codehub.api.controller.ajsServiceContollers.Applicants;

import gr.codehub.api.dto.ApplicantDTO;
import gr.codehub.api.exception.applicantException.ApplicantIdNotFoundException;
import gr.codehub.api.exception.applicantException.ApplicantLastNameNotFoundException;
import gr.codehub.api.exception.applicantException.ApplicantRegionNotFoundException;
import gr.codehub.api.model.Applicant;
import gr.codehub.api.service.ajsService.applicantService.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class ApplicantController {


    @Autowired
    private ApplicantService applicantService;

    /**
     * @return all applicants
     */
    @GetMapping("applicants")
    public List<Applicant> getAllApplicants() {
        return applicantService.getAllApplicantsFromDB();
    }

    /**
     * search applicant by the given id
     *
     * @param id applicant id
     * @return the applicant
     * @throws ApplicantIdNotFoundException
     */
    @GetMapping("applicant/{id}")
    public Applicant getAllApplicantById(@PathVariable int id) throws ApplicantIdNotFoundException {
        return applicantService.getApplicantFromDB(id);
    }

    /**
     * search applicant by last name
     *
     * @param lastName applicant last name
     * @return the applicant by the given name
     * @throws ApplicantLastNameNotFoundException
     */
    @GetMapping("applicants/{lastName}")
    public Applicant getApplicantByName(@PathVariable String lastName) throws ApplicantLastNameNotFoundException {
        return applicantService.getApplicantByNameFromDB(lastName);
    }

    /**
     * search all applicants by region
     *
     * @param region
     * @return the applicant by the given region
     * @throws ApplicantRegionNotFoundException
     */
    @GetMapping("applicantss/{region}")
    public Optional<List<Applicant>> getApplicantByRegion(@PathVariable String region) throws ApplicantRegionNotFoundException {
        Optional<List<Applicant>> applicants = applicantService.getApplicantByRegionFromDB(region);
        return applicants;
    }

    /**
     * search the applicants's skill by skill name
     *
     * @param skill applicant skill name
     * @return applicants
     */
    @GetMapping("applicants/skill/{skill}")
    public List<Applicant> getApplicantsBySkill(@PathVariable String skill) {
        List<Applicant> applicants = applicantService.getApplicantsBySkillFromDB(skill);
        return applicants;
    }

    /**
     * @return the applicants from excel
     * @throws IOException
     */
    @GetMapping("applicants/fromExcel")
    public List<Applicant> getApplicantFromExcel() throws IOException {
        return applicantService.readApplicantFromExcel();
    }

    /**
     * request for adding a new applicant
     *
     * @param applicantDTO
     * @return the new applicant
     */
    @PostMapping("newApplicant")
    public Applicant newApplicant(@RequestBody ApplicantDTO applicantDTO) {
        return applicantService.save(applicantDTO);
    }

}

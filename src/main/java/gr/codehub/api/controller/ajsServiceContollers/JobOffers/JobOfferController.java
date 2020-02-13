package gr.codehub.api.controller.ajsServiceContollers.JobOffers;

import gr.codehub.api.dto.JobOfferDTO;
import gr.codehub.api.exception.jobOfferException.JobOfferApplicantIdNotFoundException;
import gr.codehub.api.exception.jobOfferException.JobOfferIdNotFoundException;
import gr.codehub.api.exception.jobOfferException.JobOfferNameNotFoundException;
import gr.codehub.api.model.JobOffer;
import gr.codehub.api.service.ajsService.jobOfferService.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class JobOfferController {
    @Autowired
    private JobOfferService jobOfferService;

    /**
     * @return return all job offers
     */
    @GetMapping("joboffers")
    public List<JobOffer> getAllJobOffers(){
        List<JobOffer> offers = jobOfferService.getJobOffers();
        System.out.println(offers.size());
        return offers;
    }

    /**
     * search job offers by id
     * @param id job offer id
     * @return the job offer by the given id
     * @throws JobOfferIdNotFoundException
     */
    @GetMapping("joboffers/{id}")
    public JobOffer getJobOfferById(@PathVariable int id) throws JobOfferIdNotFoundException {
        JobOffer offer = jobOfferService.getJobOfferById(id);
        return offer;
    }

    /**
     * search te job offer by the given name
     * @param name job offer name
     * @return the job offer
     * @throws JobOfferNameNotFoundException
     */
    @GetMapping("joboffer/{name}")
    public List<JobOffer> getJobOfferByName(@PathVariable String name) throws JobOfferNameNotFoundException {
        List<JobOffer> offer =  jobOfferService.getJobOfferByNameFromDB(name);
        return offer;
    }


    /**
     * search the applicant by the given id
     * @param applicant_id
     * @return the offer of the given applicant
     */
    @GetMapping("joboffer/applicant/{applicant_id}")
    public List<JobOffer> getJobOfferByName(@PathVariable int applicant_id) throws JobOfferApplicantIdNotFoundException {
        List<JobOffer> offers =  jobOfferService.getJobOffersByApplicantID(applicant_id);
        return offers;
    }

    /**
     * search the job offer by skill name
     * @param skill skill name
     * @return the job offer
     */
    @GetMapping("joboffers/skill/{skill}")
    public List<JobOffer> getJobOffersBySkill(@PathVariable String skill) {
        List<JobOffer> offer =  jobOfferService.getJobOffersBySkillFromDB(skill);
        return offer;
    }


    /**
     * @return the job offers from excel
     * @throws IOException
     */
    @GetMapping("joboffers/fromExcel")
    public List<JobOffer> getJobOffersFromExcel() throws IOException {
        return jobOfferService.readJobOfferFromExcel();
    }


    /**
     * request for new job offer
     * @param jobOfferDTO
     * @return the new job offer
     */
    @PostMapping("newJobOffer")
    public JobOffer newJobOffer(@RequestBody JobOfferDTO jobOfferDTO) {
        return jobOfferService.save(jobOfferDTO);
    }
}

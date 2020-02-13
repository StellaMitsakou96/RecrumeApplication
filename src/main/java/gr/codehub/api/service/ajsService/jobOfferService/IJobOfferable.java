package gr.codehub.api.service.ajsService.jobOfferService;

import gr.codehub.api.dto.JobOfferDTO;
import gr.codehub.api.exception.jobOfferException.JobOfferApplicantIdNotFoundException;
import gr.codehub.api.exception.jobOfferException.JobOfferIdNotFoundException;
import gr.codehub.api.exception.jobOfferException.JobOfferNameNotFoundException;
import gr.codehub.api.model.JobOffer;

import java.io.IOException;
import java.util.List;

public interface IJobOfferable {
    List<JobOffer> getJobOffers();

    JobOffer getJobOfferById(int id) throws JobOfferIdNotFoundException;

    List<JobOffer> getJobOfferByNameFromDB(String name) throws JobOfferNameNotFoundException;

    List<JobOffer> getJobOffersBySkillFromDB(String skill);

    List<JobOffer> getJobOfferOfACompany(String nameOfCompany, String nameOfJob);

    JobOffer save(JobOfferDTO jobOfferDTO);

    List<JobOffer> getJobOffersByApplicantID(int id) throws JobOfferApplicantIdNotFoundException;

    List<JobOffer> readJobOfferFromExcel() throws IOException;
}

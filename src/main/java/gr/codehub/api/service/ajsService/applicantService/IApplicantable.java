package gr.codehub.api.service.ajsService.applicantService;

import gr.codehub.api.dto.ApplicantDTO;
import gr.codehub.api.exception.applicantException.ApplicantIdNotFoundException;
import gr.codehub.api.exception.applicantException.ApplicantLastNameNotFoundException;
import gr.codehub.api.exception.applicantException.ApplicantRegionNotFoundException;
import gr.codehub.api.model.Applicant;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IApplicantable {
    List<Applicant> getAllApplicantsFromDB();

    Applicant getApplicantFromDB(int id) throws ApplicantIdNotFoundException;

    Applicant getApplicantByNameFromDB(String lastName) throws ApplicantLastNameNotFoundException;

    Optional<List<Applicant>> getApplicantByRegionFromDB(String region) throws ApplicantRegionNotFoundException;

    List<Applicant> getApplicantsBySkillFromDB(String skill);

    Applicant save(ApplicantDTO applicantDTO);

    List<Applicant> readApplicantFromExcel() throws IOException;
}

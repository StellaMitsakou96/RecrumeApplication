package gr.codehub.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class JobOfferDTO {
    private String jobOfferName;
    //private Date dateOfOffer;

    private int company_id;
    private int applicant_id;
   // List<CompanyDTO> companyDTOs;
   // List<ApplicantDTO> applicantDTOs;
}

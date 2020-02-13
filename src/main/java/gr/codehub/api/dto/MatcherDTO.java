package gr.codehub.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatcherDTO {
    private int applicant_id;
    private int job_offer_id;

    @JsonIgnore
    private boolean finalized;
}

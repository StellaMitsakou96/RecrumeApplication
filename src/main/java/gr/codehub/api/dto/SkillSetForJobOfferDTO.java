package gr.codehub.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillSetForJobOfferDTO {
    private int job_offer_id;
    private int skill_id;
}

package gr.codehub.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String educationLevel;
    private String region;
}

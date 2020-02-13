package gr.codehub.api.model;


import gr.codehub.api.model.enumarator.LevelEnumarator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String address;
    private String educationLevel;
    private LevelEnumarator levelEnumarator;
    private String region;

    @OneToMany(mappedBy="applicant", cascade = CascadeType.ALL)
    private List<JobOffer> jobOffers;


    @OneToMany(mappedBy="applicant", cascade = CascadeType.ALL)
    private List<SkillSet> skillSets;

    public Applicant(String stringCellValue, String stringCellValue1, String stringCellValue2, String stringCellValue3, String stringCellValue4) {
    }


    //----------------------------------------------------------
//    @OneToOne(mappedBy = "matcher")
//    private Matcher matcher;
}

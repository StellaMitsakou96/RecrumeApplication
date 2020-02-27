package gr.codehub.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String jobOfferName;
    private LevelEnumarator levelEnumarator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Applicant applicant;


    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    private List<SkillSetForJobOffer> skillSetForJobOffers;


}

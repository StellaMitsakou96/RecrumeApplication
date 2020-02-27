package gr.codehub.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Matcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int type;


    @Column(columnDefinition = "bit default 0")
    private boolean finalized;

    @OneToOne
    private Applicant applicant;

    @OneToOne
    private JobOffer jobOffer;
}

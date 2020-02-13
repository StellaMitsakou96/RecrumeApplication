package gr.codehub.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SkillSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Applicant applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Skill skill;


    public SkillSet(String stringCellValue) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillSet skillSet = (SkillSet) o;
        return skill.equals(skillSet.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill);
    }
}

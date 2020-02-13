package gr.codehub.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String skillName;

    @Column(columnDefinition = "bit default 1")
    private boolean active;

    //private List<Skill> skills;

    @OneToMany(mappedBy="skill", cascade = CascadeType.ALL)
    private List<SkillSet> skillSets;


    //----------------------------------------------------------
    @OneToMany(mappedBy="skill", cascade = CascadeType.ALL)
    private List<SkillSetForJobOffer> skillSetForJobOffers;

    public Skill(String skillName)
{
    this.skillName=skillName;
}
}

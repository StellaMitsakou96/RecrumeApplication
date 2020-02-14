package gr.codehub.api.service.reportingService;

import gr.codehub.api.model.Skill;
import gr.codehub.api.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingService implements IReportable{
    private SkillRepository skillRepo;

    @Autowired
    public ReportingService(SkillRepository skillRepo) {
        this.skillRepo = skillRepo;
    }

    /**
     * find most used Skills by their frequency of appearance
     * @return skill name and their frequency of use
     */
    public List<String> findMostFrequent(){
        List<Object[]> query = skillRepo.findMostRequested(); //Sou gyrnaei monodiastato pinaka me 2 theseis.
        List<String> frequency = new ArrayList<>();
        for(Object[] o: query){
            frequency.add(o[0] + "(" + o[1] + ")");
        }
        return frequency;
    }

}

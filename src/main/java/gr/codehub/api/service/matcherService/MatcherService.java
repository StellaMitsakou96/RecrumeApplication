package gr.codehub.api.service.matcherService;


import gr.codehub.api.dto.MatcherDTO;
import gr.codehub.api.model.*;
import gr.codehub.api.repositories.ApplicantRepository;
import gr.codehub.api.repositories.JobOfferRepository;
import gr.codehub.api.repositories.MatcherRepository;
import gr.codehub.api.repositories.SkillSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class MatcherService implements IMatcherable{
    private MatcherRepository matchersRepo;

    private ApplicantRepository applicantsRepo;

    private JobOfferRepository jobOffersRepo;

    private SkillSetRepository skillSetsRepo;

    @Autowired
    public MatcherService(MatcherRepository matchersRepo, ApplicantRepository applicantsRepo, JobOfferRepository jobOffersRepo, SkillSetRepository skillSetsRepo) {
        this.matchersRepo = matchersRepo;
        this.jobOffersRepo = jobOffersRepo;
        this.applicantsRepo = applicantsRepo;
        this.skillSetsRepo = skillSetsRepo;
    }

    public Matcher getManualMatch(MatcherDTO matcherDTO1) {
        Matcher matcher = new Matcher();
        matcher.setJobOffer(jobOffersRepo.findFirstById(matcherDTO1.getJob_offer_id()));
        matcher.setApplicant(applicantsRepo.findFirstById(matcherDTO1.getApplicant_id()));
        return matchersRepo.save(matcher);
    }

    public Matcher getAutomatedMatch(MatcherDTO matcherDTO1) {
        System.out.println("----------------------------------------------------------------------------------");
        List<JobOffer> jobOffers = jobOffersRepo.findByApplicantIdIsNull();  //Pare tin job offer pou exei applicant id iso me null
        System.out.println(jobOffers.size());
        Applicant applicant = applicantsRepo.findFirstById(matcherDTO1.getApplicant_id()); //pare ton dwsmeno applicant apo ti vash
        System.out.println(applicant.getSkillSets().get(0).getSkill().getSkillName());
        for (JobOffer jobOffer : jobOffers) { //Dietrekse to table job offer me applicant_id == null
            //Pare ta skills tou antisoixou job offer kai valta sth lista.
            //boolean thisJobNotCovered = false;
            int counter = 0;
            System.out.println("setting counters...");
            for (SkillSetForJobOffer aS : jobOffer.getSkillSetForJobOffers()) {  //Dietrekse ti lista apo ta skill tou job offer pou eftiakses apo panw
                String skillName = aS.getSkill().getSkillName(); //Pairnei to onoma tou skill tou job
                System.out.println("JobSkill: " + skillName);
                for (SkillSet sk :applicant.getSkillSets()) {
                    System.out.println("Applicant name:" + sk.getSkill().getSkillName());
                    if (sk.getSkill().getSkillName().equals(skillName)) { //elegxei an ta dyo skill einai idia.
                        System.out.println("found one");
                        counter++;
                        break;
                    }
                }
                System.out.println("TI GINETAI EDO "+jobOffer.getSkillSetForJobOffers().size() );
                if(counter == jobOffer.getSkillSetForJobOffers().size() ){
                    Matcher matcher = new Matcher();
                    matcher.setJobOffer(jobOffer);
                    matcher.setApplicant(applicant);
                    matcher.setType(1);
                    Applicant app = new Applicant();
                    app.setId(matcherDTO1.getApplicant_id());  //Auto ginetai gia na valei ston pinaka me ta job offer ssti thesi tou applicant id to id
                    jobOffer.setApplicant(app);
                    return matchersRepo.save(matcher);
                }
            }
        }
        System.out.println("----------------------------------------------------------------------------------");
        return null;
    }

    public Matcher updateOne(int id, MatcherDTO matcherDTO) {
        Matcher matcher = matchersRepo.findById(id);
        matcher.setApplicant(applicantsRepo.findFirstById(matcherDTO.getApplicant_id()));
        matcher.setJobOffer(jobOffersRepo.findFirstById(matcherDTO.getJob_offer_id()));
        return matchersRepo.save(matcher);
    }

    public String finalizedMatch(int id) {
        Matcher matcher = matchersRepo.findById(id);
        matcher.setFinalized(true);
        matchersRepo.save(matcher);
        return "Your match is finalized";
    }

    public String finalizedAutomatedMatch(int id) {
        Matcher matcher = matchersRepo.findById(id);
        matcher.setFinalized(true);
        matchersRepo.save(matcher);
        return "Your match is finalized";
    }

}

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

    /**
     * create the manual match
     * @param matcherDTO1
     * @return the match and save throw the suitable repository in DB
     */
    public Matcher getManualMatch(MatcherDTO matcherDTO1) {
        Matcher matcher = new Matcher();
        matcher.setJobOffer(jobOffersRepo.findFirstById(matcherDTO1.getJob_offer_id()));
        matcher.setApplicant(applicantsRepo.findFirstById(matcherDTO1.getApplicant_id()));
        return matchersRepo.save(matcher);
    }

    /**
     * create an automated match by a given applicant_id
     * @param matcherDTO1
     * @return the automated match
     */
    public Matcher getAutomatedMatch(MatcherDTO matcherDTO1) {
        List<JobOffer> jobOffers = jobOffersRepo.findByApplicantIdIsNull();  //Pare tin job offer pou exei applicant id iso me null
        Applicant applicant = applicantsRepo.findFirstById(matcherDTO1.getApplicant_id()); //pare ton dwsmeno applicant apo ti vash
        for (JobOffer jobOffer : jobOffers) {
            int counter = 0;
            for (SkillSetForJobOffer aS : jobOffer.getSkillSetForJobOffers()) {  //Dietrekse ti lista apo ta skill tou job offer pou eftiakses apo panw
                String skillName = aS.getSkill().getSkillName(); //Pairnei to onoma tou skill tou job
                for (SkillSet sk :applicant.getSkillSets()) {
                    if (sk.getSkill().getSkillName().equals(skillName)) { //elegxei an ta dyo skill einai idia.
                        counter++;
                        break;
                    }
                }
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
        return null;
    }

    /**
     * update a manual match
     * @param id
     * @param matcherDTO
     * @return the updated match
     */
    public Matcher updateOne(int id, MatcherDTO matcherDTO) {
        Matcher matcher = matchersRepo.findById(id);
        matcher.setApplicant(applicantsRepo.findFirstById(matcherDTO.getApplicant_id()));
        matcher.setJobOffer(jobOffersRepo.findFirstById(matcherDTO.getJob_offer_id()));
        return matchersRepo.save(matcher);
    }

    /**
     * finalize a manual match - soft delete of the match
     * @param id
     * @return a String message
     */
    public String finalizedMatch(int id) {
        Matcher matcher = matchersRepo.findById(id);
        matcher.setFinalized(true);
        matchersRepo.save(matcher);
        return "Your match is finalized";
    }


    /**
     * soft delete of an automated match
     * @param id
     * @return a String message
     */
    public String finalizedAutomatedMatch(int id) {
        Matcher matcher = matchersRepo.findById(id);
        matcher.setFinalized(true);
        matchersRepo.save(matcher);
        return "Your match is finalized";
    }

}

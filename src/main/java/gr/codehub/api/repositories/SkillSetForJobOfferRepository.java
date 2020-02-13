package gr.codehub.api.repositories;

import gr.codehub.api.model.JobOffer;
import gr.codehub.api.model.SkillSetForJobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillSetForJobOfferRepository extends JpaRepository<SkillSetForJobOffer, Integer> {
    @Query(value = "SELECT *  FROM skill_set_for_job_offer WHERE skill_from_recrume_id = :skill", nativeQuery = true)
    Optional<List<JobOffer>> findAllBySkillFromRecrume(@Param("skill") String skill);
}

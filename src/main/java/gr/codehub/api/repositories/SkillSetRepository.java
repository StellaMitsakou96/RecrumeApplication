package gr.codehub.api.repositories;

import gr.codehub.api.model.SkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillSetRepository extends JpaRepository<SkillSet, Integer> {
}







package gr.codehub.api.repositories;

import gr.codehub.api.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    Skill findById(int id);

    Skill findFirstById(int skill_id);

    Skill findBySkillName(String skillName);


    @Query(value = "SELECT TOP 5 skill.skill_name, COUNT (skill.skill_name) FROM skill\n" +
            "            INNER JOIN skill_set\n" +
            "            ON skill.id = skill_set.skill_id\n" +
            "            GROUP BY skill.skill_name\n" +
            "            ORDER BY COUNT (skill.skill_name) DESC;", nativeQuery = true)
    List<Object[]> findMostRequested();


}

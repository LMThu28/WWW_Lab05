package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.CandidateSkill;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Skill> {
    @Query("SELECT c FROM Candidate c JOIN c.candidateSkills cs WHERE cs.skill.id = :skillId")
    List<Candidate> findCandidatesBySkillId(@Param("skillId") Long skillId);
}

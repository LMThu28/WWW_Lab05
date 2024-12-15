package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByType(SkillType type);

    Optional<Skill> findBySkillName(String skillName);

    @Query("SELECT s FROM Skill s " +
            "JOIN s.jobSkills jk " +
            "JOIN jk.job j " +
            "WHERE j.company.id = :companyId")
    List<Skill> findSkillByCompany(@Param("companyId") Long companyId);

    @Query("SELECT s FROM Skill s WHERE s NOT IN (SELECT cs.skill FROM CandidateSkill cs WHERE cs.candidate.id = :candidateId)")
    List<Skill> findSkillCandidateShouldLearn(@Param("candidateId") Long candidateId);


    @Query("SELECT s FROM Skill s " +
            "JOIN CandidateSkill cs ON cs.skill = s " +
            "JOIN Candidate c ON cs.candidate = c " +
            "WHERE c.id = :candidateId")
    List<Skill> findSkillsByCandidateId(@Param("candidateId") Long candidateId);

}

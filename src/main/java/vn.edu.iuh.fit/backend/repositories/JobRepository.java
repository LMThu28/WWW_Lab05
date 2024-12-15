package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
//    List<Job> findByCandidateId(Long candID);
@Query("SELECT j FROM Job j JOIN j.jobSkills js WHERE js.skill.id = :skillId")
@Transactional(readOnly = true)
List<Job> findBySkillId(@Param("skillId") Long skillId);

    @Query("SELECT j FROM Job j WHERE LOWER(j.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Job> findByTitleContainingIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT js.skill FROM JobSkill js WHERE js.job.id = :jobId")

    public List<Skill> findSkillsByJobId(@Param("jobId") Long jobId);

    public List<Job> findAllByCompany_Id(long id);

    @Query("SELECT s.skill FROM JobSkill s WHERE s.job.id = :jobId")
    List<Skill> findSkillsByJob_Id(@Param("jobId") Long jobId);

    @Modifying
    @Query("select j from Job j join j.jobSkills jk join jk.skill s join s.candidateSkills ck" +
            " where ck.candidate.id = :candidateID and ck.candidate.address.city = j.company.address.city")
    public List<Job> findAllJobMatchWithCandidate(@Param("candidateID") long candidateID);


    @Query("SELECT j FROM Job j WHERE j.id IN " +
            "(SELECT js.job.id FROM JobSkill js " +
            "WHERE js.skill.id IN (SELECT cs.skill.id FROM CandidateSkill cs WHERE cs.candidate.id = :candidateId))")
    List<Job> findAllJobMatchWithCandidate(@Param("candidateId") Long candidateId);

    List<Job> findByCompanyId(Long companyId);
}

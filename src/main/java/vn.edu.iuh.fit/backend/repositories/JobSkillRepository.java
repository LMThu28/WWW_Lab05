package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.backend.models.JobSkill;

import java.util.List;

public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {

    @Query("SELECT js FROM JobSkill js WHERE js.job.id = :jobId")
    List<JobSkill> findByJobId(@Param("jobId") Long jobId);

}

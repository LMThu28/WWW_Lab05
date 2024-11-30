package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.models.Job;
import vn.edu.iuh.fit.models.JobSkill;

import java.util.List;

@Repository
public class JobSkillDao {
    private final EntityManager manager;

    @Autowired
    public JobSkillDao(EntityManager entityManager) {
        this.manager = entityManager;
    }
    @Transactional
    public boolean addJobSkill(Job job, List<JobSkill> jobSkills) {

        try {
            manager.persist(job);
            for (JobSkill js:jobSkills) {
                js.setJob(job); //set id của jobskill=job
                manager.persist(js);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    @Transactional
    public Integer calcProposedSalary(Long job_id){
        Integer rs= 0;
        try {
            if(manager.find(Job.class, job_id)==null) rs=0;
            else{
                String sql="SELECT SUM(500*SkillLevel+300) AS Salary FROM job_skill\n" +
                        "WHERE JobID=?";
                Query query= manager.createNativeQuery(sql);
                query.setParameter(1, job_id);
                rs= Integer.parseInt(query.getSingleResult().toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

}

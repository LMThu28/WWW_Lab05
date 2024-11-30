package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.models.Candidate;
import vn.edu.iuh.fit.models.Job;
import vn.edu.iuh.fit.models.Skill;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CandidateSkillDao {
    private final EntityManager manager;

    @Autowired
    public CandidateSkillDao(EntityManager entityManager) {
        this.manager = entityManager;
    }
    @Transactional
    public List<Job> getJobForCandidateOrderBySkil(long cand_id){
        List<Job> list= new ArrayList<>();
        try {
            String relativeQuery="SELECT DISTINCT job.JobID, job.Description, job.JobName, job.CompanyID \n" +
                    "FROM job\n" +
                    "INNER JOIN job_skill ON job.JobID=job_skill.JobID\n" +
                    "WHERE job_skill.SkillID IN (\n" +
                    "SELECT SkillID FROM candidate_skill\n" +
                    "WHERE CandidateID=? \n" +
                    ")";
            Query query=manager.createNativeQuery(relativeQuery, Job.class);
            query.setParameter(1, cand_id);
            list=query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    @Transactional
    public List<Skill> getSkillNotHaveToLearn(long cand_id){
        List<Skill> list= new ArrayList<>();
        try {
            if(manager.find(Candidate.class,cand_id)==null) return list;
            String sql="SELECT *\n" +
                    "FROM skill\n" +
                    "WHERE skill.SkillID NOT IN (\n" +
                    "SELECT SkillID FROM candidate_skill\n" +
                    "WHERE CandidateID=?\n" +
                    ")";
            Query query=manager.createNativeQuery(sql, Skill.class);
            query.setParameter(1, cand_id);
            list=query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    @Transactional
    public List<Candidate> getCandidateOrderBySkill(long skill_id){
        List<Candidate> list= new ArrayList<>();
        try {
            String sql="SELECT candidate.CandidateID, candidate.DOB, candidate.Email, candidate.FullName, candidate.Phone, candidate.AddressID\n" +
                    "FROM candidate\n" +
                    "INNER JOIN candidate_skill ON candidate_skill.CandidateID= candidate.CandidateID\n" +
                    "WHERE candidate_skill.SkillID=?";
            Query query=manager.createNativeQuery(sql, Candidate.class);
            query.setParameter(1, skill_id);
            list=query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.models.Skill;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SkillDao {
    private final EntityManager manager;

    @Autowired
    public SkillDao(EntityManager entityManager) {
        this.manager = entityManager;
    }
    @Transactional
    public boolean add(Skill skill){
        try {
            manager.persist(skill);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public boolean update(Skill skill){
        try {
            manager.merge(skill);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public boolean delete(long id){
        try {
            Skill skill=manager.find(Skill.class, id);
            skill.setSkill_type(0);
            manager.merge(skill);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }
    @Transactional
    public boolean active(long id){
        try {
            Skill skill=manager.find(Skill.class, id);
            skill.setSkill_type(1);
            manager.merge(skill);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public Skill get(long id){
        try {
            return manager.find(Skill.class,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Transactional
    public List<Skill> getAllSkillList(){
        try {
            return manager.createQuery("select s from Skill s", Skill.class).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Transactional
    public Page<Skill> getAll(int page, int size) {
        try {
            //số job hiển thị
            List<Skill> skills = manager.createQuery("select s from Skill s", Skill.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();
            //tổng số job
            long totalSkills = (long) manager.createQuery("select count(s) from Skill s").getSingleResult();

            return new PageImpl<>(skills, PageRequest.of(page, size), totalSkills);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public List<Skill> getSkillsForJob(long jobID){
        List<Skill> list= new ArrayList<>();
        try {
            String sql="SELECT skill.SkillID, skill.Description, skill.SkillName, skill.Type FROM job \n" +
                    "INNER JOIN job_skill ON job.JobID=job_skill.JobID\n" +
                    "INNER JOIN skill ON skill.SkillID=job_skill.SkillID\n" +
                    "WHERE job.JobID=?";
            Query query= manager.createNativeQuery(sql, Skill.class);
            query.setParameter(1, jobID);
            list=query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

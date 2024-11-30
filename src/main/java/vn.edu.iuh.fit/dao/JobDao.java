package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.models.Job;

import java.util.List;

@Repository
public class JobDao {
    private final EntityManager manager;

    @Autowired
    public JobDao(EntityManager entityManager) {
        this.manager = entityManager;
    }
    @Transactional
    public List<Job> getAllJobList(){
        try {
            return manager.createQuery("select j from Job j", Job.class).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Transactional
    public Page<Job> getAll(int page, int size) {
        try {
            //số job hiển thị
            List<Job> jobList = manager.createQuery("select j from Job j", Job.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();
            //tổng số job
            long totalJobs = (long) manager.createQuery("select count(j) from Job j").getSingleResult();

            return new PageImpl<>(jobList, PageRequest.of(page, size), totalJobs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}

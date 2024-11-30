package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.models.Candidate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ExperienceDao {
    private final EntityManager manager;

    @Autowired
    public ExperienceDao(EntityManager entityManager) {
        this.manager = entityManager;
    }
    @Transactional
    public List<Candidate> getMaxExpYearCandidate(){
        List<Candidate> list= new ArrayList<>();
        try {
            String sql= "SELECT candidate.CandidateID, (YEAR(experience.ToDate) - YEAR(experience.FromDate)) AS ExpYear\n" +
                    "FROM candidate\n" +
                    "INNER JOIN experience ON experience.CandidateID = candidate.CandidateID\n" +
                    "WHERE (YEAR(experience.ToDate) - YEAR(experience.FromDate)) = (\n" +
                    "    SELECT MAX(YEAR(experience.ToDate) - YEAR(experience.FromDate))\n" +
                    "    FROM candidate\n" +
                    "    INNER JOIN experience ON experience.CandidateID = candidate.CandidateID\n" +
                    ")";
            List<Object[]> objects=manager.createNativeQuery(sql, Object[].class).getResultList();
            for (Object[] obj:objects) {
                list.add(manager.find(Candidate.class,(Long)obj[0]));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    //Ds ứng viên chưa có kinh nghiệm
    @Transactional
    public List<Candidate> getNotHaveExpCandidate(){
        List<Candidate> list= new ArrayList<>();
        try {
            String sql="SELECT * FROM candidate\n" +
                    "WHERE candidate.CandidateID NOT IN(\n" +
                    "SELECT DISTINCT CandidateID FROM experience\n" +
                    ")";
            list=manager.createNativeQuery(sql, Candidate.class).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

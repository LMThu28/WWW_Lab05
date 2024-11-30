package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.models.Candidate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CandidateDao {

    private final EntityManager entityManager;

    @Autowired
    public CandidateDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public boolean logIn(long cand_id, String pass) {
        try {
            Candidate candidate = entityManager.find(Candidate.class, cand_id);
            // Lấy phone làm pass
            if (candidate != null && candidate.getPhone().equalsIgnoreCase(pass)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    public Map<Integer, Long> getCandByYearBorn() {
        Map<Integer, Long> map = new HashMap<>();
        try {
            String sql= "SELECT YEAR(Dob) AS YearBorn, COUNT(*) AS Total\n" +
                    "FROM candidate\n" +
                    "GROUP BY YEAR(Dob)";
            List<Object[]> objects= entityManager.createNativeQuery(sql, Object[].class).getResultList();
            for (Object[] obj : objects) {
                map.put((Integer) obj[0], (Long) obj[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Transactional
    public Map<Candidate, Long> getTotalSkillByCand() {
        Map<Candidate, Long> map = new HashMap<>();
        try {
            String sql="SELECT CandidateID, COUNT(*) AS TotalSkill \n" +
                    "FROM candidate_skill \n" +
                    "GROUP BY CandidateID ";
            Query query = entityManager.createNativeQuery(sql, Object[].class);
            List<Object[]> objects = query.getResultList();
            for (Object[] obj : objects) {
                map.put(entityManager.find(Candidate.class,obj[0]), (Long) obj[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Transactional
    public Map<Candidate, Long> getTotalJobSkillByCand() {
        Map<Candidate, Long> map = new HashMap<>();
        try {
            String sql= "SELECT CandidateID, COUNT(*) AS TotalJob \n" +
                    "FROM candidate_skill\n" +
                    "INNER JOIN skill ON skill.SkillID=candidate_skill.SkillID\n" +
                    "INNER JOIN job_skill ON job_skill.SkillID=skill.SkillID\n" +
                    "GROUP BY CandidateID";
            List<Object[]> objects= entityManager.createNativeQuery(sql, Object[].class).getResultList();
            for (Object[] obj : objects) {
                map.put(entityManager.find(Candidate.class,obj[0]), (Long) obj[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}

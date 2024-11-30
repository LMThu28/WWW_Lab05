package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.models.Company;

@Repository
public class CompanyDao {
    private final EntityManager manager;

    @Autowired
    public CompanyDao(EntityManager entityManager) {
        this.manager = entityManager;
    }
    @Transactional
    public boolean logIn(long comp_id, String pass){

        try {
            Company company=manager.find(Company.class, comp_id);
            //lấy phone làm pass
            if(company.getPhone().equalsIgnoreCase(pass))
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

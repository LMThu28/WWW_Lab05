package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByCompanyLoginAndCompanyPassword(String companyLogin, String companyPassword);

}

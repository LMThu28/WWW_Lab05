package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;

import java.util.List;

@Service
public class CompanyServices {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
    }
    public Company authenticate(String username, String password) {
        return companyRepository.findByCompanyLoginAndCompanyPassword(username, password);
    }
}

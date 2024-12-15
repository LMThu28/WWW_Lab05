package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.edu.iuh.fit.backend.models.Address;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication(scanBasePackages = "vn.edu.iuh.fit")
public class LeMinhThu21025781Lab05Application {
    public static void main(String[] args) {
        SpringApplication.run(LeMinhThu21025781Lab05Application.class, args);
    }

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyRepository companyRepository;

//    @Bean
    CommandLineRunner initData() {
        return args -> {
            ThreadLocalRandom rnd = ThreadLocalRandom.current();

            // Generate 500 candidates
            for (int i = 1; i <= 500; i++) {
                // Random address
                Address address = new Address(
                        String.valueOf(rnd.nextInt(1, 1000)),
                        "Street " + rnd.nextInt(1, 100),
                        "City " + rnd.nextInt(1, 50),
                        String.valueOf(rnd.nextInt(70000, 80000)),
                        CountryCode.VN
                );
                addressRepository.save(address);

                // Random candidate login and password
                String candidateLogin = "candidate" + i;
                String candidatePassword = "pass" + rnd.nextInt(1000, 9999);

                // Random date of birth
                int year = rnd.nextInt(1990, 2000);
                int month = rnd.nextInt(1, 13);
                int day = rnd.nextInt(1, YearMonth.of(year, month).lengthOfMonth() + 1);

                Candidate candidate = new Candidate(
                        "Candidate Name #" + i,
                        LocalDate.of(year, month, day),
                        address,
                        String.valueOf(rnd.nextLong(1111111111L, 9999999999L)),
                        "email_" + i + "@gmail.com",
                        candidateLogin,
                        candidatePassword
                );
                candidateRepository.save(candidate);

                System.out.println("Added Candidate: " + candidate);
            }

            // Generate 100 companies
            for (int i = 1; i <= 100; i++) {
                // Tạo ngẫu nhiên địa chỉ cho công ty
                Address address = createRandomAddress(rnd);
                addressRepository.save(address);

                // Tạo ngẫu nhiên thông tin cho công ty
                String companyLogin = "company" + i;
                String companyPassword = "comp" + rnd.nextInt(1000, 9999);
                String about = "About company #" + i + " provides excellent products and services.";
                String email = "contact_" + i + "@business.com"; // email
                String phone = String.valueOf(rnd.nextLong(1000000000L, 9999999999L)); // phone
                String webURL = "http://www.company" + i + ".com"; // website URL
                String name = "Company Name #" + i;

                long id = rnd.nextLong(100000, 999999); // Random company id

                Company company = new Company(
                        companyPassword,    // companyPassword
                        companyLogin,       // companyLogin
                        about,              // about
                        email,              // email
                        phone,              // phone
                        webURL,             // webURL
                        address,            // address
                        name,               // name
                        id                  // id
                );
                companyRepository.save(company);

                System.out.println("Added Company: " + company);
            }
        };
    }
    private Address createRandomAddress(ThreadLocalRandom rnd) {
        return new Address(
                String.valueOf(rnd.nextInt(1, 1000)),
                "Street " + rnd.nextInt(1, 100),
                "City " + rnd.nextInt(1, 50),
                String.valueOf(rnd.nextInt(70000, 80000)),
                CountryCode.VN
        );
    }

}

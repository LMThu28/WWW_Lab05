package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private long id;

    @Column(name = "comp_name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @Column(name = "web_url")
    private String webURL;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "about", length = 2000)
    private String about;
    @Column(name = "company_login", nullable = false)
    private String companyLogin;
    @Column(name = "company_password", nullable = false)
    private String companyPassword;
    //==================RELATIONSHIPS=====================
    @OneToMany(mappedBy = "company")
//    @JoinColumn(name = "jobs")
    private List<Job> jobs;

    public Company(String name, Address address, String email, String companyLogin, String companyPassword) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.companyLogin = companyLogin;
        this.companyPassword = companyPassword;
    }

    public Company(String companyPassword, String companyLogin, String about, String email, String phone, String webURL, Address address, String name, long id) {
        this.companyPassword = companyPassword;
        this.companyLogin = companyLogin;
        this.about = about;
        this.email = email;
        this.phone = phone;
        this.webURL = webURL;
        this.address = address;
        this.name = name;
        this.id = id;
    }
}
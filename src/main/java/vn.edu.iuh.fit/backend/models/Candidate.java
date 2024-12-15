package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "candidate")
@Data
@NoArgsConstructor
public class Candidate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "can_id")
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "dob", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @OneToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @Column(name = "phone", length = 15, nullable = false, unique = true)
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private int status = 1;
    @Column(name = "candidate_login", nullable = false, unique = true)
    private String candidateLogin;
    @Column(name = "candidate_password", nullable = false)
    private String candidatePassword;
    //====================RELATIONSHIPS========================
    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private List<CandidateSkill> candidateSkills;
    public Candidate(String fullName, LocalDate dob, Address address, String phone, String email) {
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Candidate(String fullName, LocalDate dob, Address address, String phone, String email, String candidateLogin, String candidatePassword) {
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.candidateLogin = candidateLogin;
        this.candidatePassword = candidatePassword;
    }


}
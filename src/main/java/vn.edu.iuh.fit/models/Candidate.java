package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name ="candidate")
public class Candidate {
    @Id
    @Column(name="CandidateID")
    private long cand_id;
    @Column(name="DOB")
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @Column(name="Email",columnDefinition = "varchar(255)")
    private String email;
    @Column(name="FullName",columnDefinition = "varchar(255)")
    private String full_name;
    @Column(name="Phone",columnDefinition = "varchar(15)")
    private String phone;
    @ManyToOne
    @JoinColumn(name = "AddressID")
    private Address address;

    public Candidate() {
    }

    public long getCand_id() {
        return cand_id;
    }

    public void setCand_id(long cand_id) {
        this.cand_id = cand_id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Candidate(long cand_id, LocalDate dob, String email, String full_name, String phone, Address address) {
        this.cand_id = cand_id;
        this.dob = dob;
        this.email = email;
        this.full_name = full_name;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "cand_id=" + cand_id +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", full_name='" + full_name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}
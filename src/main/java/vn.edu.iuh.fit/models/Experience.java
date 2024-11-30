package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @Column(name = "ExperienceID")
    private long exp_id;
    @Column(name="Company",columnDefinition = "varchar(120)")
    private String company;
    @Column(name="FromDate")
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate from_date;
    @Column(name="Role",columnDefinition = "varchar(100)")
    private String role;
    @Column(name="ToDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate to_date;
    @Column(name="Description",columnDefinition = "varchar(400)")
    private String work_desc;
    @ManyToOne
    @JoinColumn(name = "CandidateID")
    private Candidate candidate;

    public Experience() {
    }

    public Experience(long exp_id, String company, LocalDate from_date, String role, LocalDate to_date, String work_desc, Candidate candidate) {
        this.exp_id = exp_id;
        this.company = company;
        this.from_date = from_date;
        this.role = role;
        this.to_date = to_date;
        this.work_desc = work_desc;
        this.candidate = candidate;
    }

    public long getExp_id() {
        return exp_id;
    }

    public void setExp_id(long exp_id) {
        this.exp_id = exp_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDate from_date) {
        this.from_date = from_date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDate to_date) {
        this.to_date = to_date;
    }

    public String getWork_desc() {
        return work_desc;
    }

    public void setWork_desc(String work_desc) {
        this.work_desc = work_desc;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "exp_id=" + exp_id +
                ", company='" + company + '\'' +
                ", from_date=" + from_date +
                ", role='" + role + '\'' +
                ", to_date=" + to_date +
                ", work_desc='" + work_desc + '\'' +
                ", candidate=" + candidate +
                '}';
    }
}
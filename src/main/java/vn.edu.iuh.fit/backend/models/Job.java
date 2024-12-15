package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "job")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private long id;
    @Column(name = "job_name", nullable = false)
    private String name;
    @Column(name = "job_desc", length = 2000, nullable = false)
    private String description;


    //====================RELATIONSHIPS========================
    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @OneToMany(mappedBy = "job")
    private List<JobSkill> jobSkills;


    public Job(long id, Company company, String description, String name) {
        this.id = id;
        this.company = company;
        this.description = description;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", company=" + company +
                ", jobSkills=" + jobSkills +
                '}';
    }
}
package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @Column(name = "JobID")
    private long job_id;
    @Column(name="JobName",columnDefinition = "varchar(150)")
    private String job_name;
    @Column(name="Description",columnDefinition = "varchar(300)")
    private String job_desc;
    @ManyToOne
    @JoinColumn(name = "CompanyID")
    private Company company;

    public Job() {
    }

    public Job(long job_id, String job_name, String job_desc, Company company) {
        this.job_id = job_id;
        this.job_name = job_name;
        this.job_desc = job_desc;
        this.company = company;
    }

    public long getJob_id() {
        return job_id;
    }

    public void setJob_id(long job_id) {
        this.job_id = job_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_desc() {
        return job_desc;
    }

    public void setJob_desc(String job_desc) {
        this.job_desc = job_desc;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Job{" +
                "job_id=" + job_id +
                ", job_name='" + job_name + '\'' +
                ", job_desc='" + job_desc + '\'' +
                ", company=" + company +
                '}';
    }
}
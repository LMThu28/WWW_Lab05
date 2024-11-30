package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Job_skill")
public class JobSkill {
    @Id
    @ManyToOne
    @JoinColumn(name = "JobID")
    private Job job;
    @Id
    @ManyToOne
    @JoinColumn(name = "SkillID")
    private Skill skill;
    @Column(name = "MoreInfos", columnDefinition = "varchar(1000)")
    private String more_infos;
    @Column(name = "SkillLevel", columnDefinition = "tinyint(4)")
    private int skill_level;

    public JobSkill() {
    }

    public JobSkill(Skill skill, String more_infos, int skill_level) {
        this.skill = skill;
        this.more_infos = more_infos;
        this.skill_level = skill_level;
    }

    public JobSkill(Job job, Skill skill, String more_infos, int skill_level) {
        this.job = job;
        this.skill = skill;
        this.more_infos = more_infos;
        this.skill_level = skill_level;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public String getMore_infos() {
        return more_infos;
    }

    public void setMore_infos(String more_infos) {
        this.more_infos = more_infos;
    }

    public int getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(int skill_level) {
        this.skill_level = skill_level;
    }

    @Override
    public String toString() {
        return "JobSkill{" +
                "job=" + job +
                ", skill=" + skill +
                ", more_infos='" + more_infos + '\'' +
                ", skill_level=" + skill_level +
                '}';
    }
}
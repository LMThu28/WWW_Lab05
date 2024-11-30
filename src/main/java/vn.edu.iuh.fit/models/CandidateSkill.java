package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate_skill")
public class CandidateSkill {
    @Id
    @ManyToOne
    @JoinColumn(name = "CandidateID")
    private Candidate candidate;
    @Id
    @ManyToOne
    @JoinColumn(name = "SkillID")
    private Skill skill;
    @Column(name = "MoreInfos", columnDefinition = "varchar(1000)")
    private String more_infos;
    @Column(name = "SkillLevel", columnDefinition = "tinyint(4)")
    private int skill_level;

    public CandidateSkill() {
    }

    public CandidateSkill(Candidate candidate, Skill skill, String more_infos, int skill_level) {
        this.candidate = candidate;
        this.skill = skill;
        this.more_infos = more_infos;
        this.skill_level = skill_level;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
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
        return "CandidateSkill{" +
                "candidate=" + candidate +
                ", skill=" + skill +
                ", more_infos='" + more_infos + '\'' +
                ", skill_level=" + skill_level +
                '}';
    }
}
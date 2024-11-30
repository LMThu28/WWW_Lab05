package vn.edu.iuh.fit.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @Column(name="SkillID")
    private long skill_id;
    @Column(name="SkillName",columnDefinition = "varchar(150)")
    private String skill_name;
    @Column(name="Description",columnDefinition = "varchar(300)")
    private String skill_desc;
    @Column(name="Type", columnDefinition = "tinyint(4)")
    private int skill_type;

    public Skill() {
    }

    public Skill(long skill_id) {
        this.skill_id = skill_id;
    }
    public Skill(long skill_id, String skill_name, String skill_desc, int skill_type) {
        this.skill_id = skill_id;
        this.skill_name = skill_name;
        this.skill_desc = skill_desc;
        this.skill_type = skill_type;
    }

    public long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(long skill_id) {
        this.skill_id = skill_id;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public String getSkill_desc() {
        return skill_desc;
    }

    public void setSkill_desc(String skill_desc) {
        this.skill_desc = skill_desc;
    }

    public int getSkill_type() {
        return skill_type;
    }

    public void setSkill_type(int skill_type) {
        this.skill_type = skill_type;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skill_id=" + skill_id +
                ", skill_name='" + skill_name + '\'' +
                ", skill_desc='" + skill_desc + '\'' +
                ", skill_type=" + skill_type +
                '}';
    }
}
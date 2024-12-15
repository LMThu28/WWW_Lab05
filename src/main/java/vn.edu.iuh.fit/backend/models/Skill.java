package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import vn.edu.iuh.fit.backend.enums.SkillType;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//Read skill list at: https://www.yourdictionary.com/articles/examples-skills-list
//API: https://github.com/workforce-data-initiative/skills-api/wiki/API-Overview#swagger-ui-test-client
@Entity
@Table(name = "skill")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private long id;
    @Column(name = "skill_name", nullable = false, length = 150)
    private String skillName;
    @Enumerated(EnumType.STRING)
    @Column(name = "skill_type", nullable = false)
    private SkillType type;
    @Column(name = "skill_desc", nullable = false, length = 300)
    private String skillDescription;
    @OneToMany(mappedBy = "skill")
    private Set<CandidateSkill> candidateSkills = new LinkedHashSet<>();
    //====================
    @OneToMany(mappedBy = "skill")
    private List<JobSkill>jobSkills;

    public Skill(String skillDescription, SkillType type, String skillName, long id) {
        this.skillDescription = skillDescription;
        this.type = type;
        this.skillName = skillName;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                ", type=" + type +
                ", skillDescription='" + skillDescription + '\'' +
                ", jobSkills=" + jobSkills +
                '}';
    }
}
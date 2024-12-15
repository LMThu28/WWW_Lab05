package vn.edu.iuh.fit.backend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;

import java.util.List;

@Service
public class SkillServices {

    @Autowired
    private SkillRepository skillRepository;

    public void saveSkill(Skill skill) {
        skillRepository.save(skill);
    }

    public List<Skill> getSkillsByType(SkillType type) {
        return skillRepository.findByType(type);
    }
    public Skill getSkillById(Long skillId) {
        return skillRepository.findById(skillId).orElse(null);
    }


    public List<Skill> getAllSkills() {
        return skillRepository.findAll();  // Giả sử bạn có một repository `skillRepository`
    }


    public Skill findByName(String skillName) {
        return skillRepository.findBySkillName(skillName)
                .orElseThrow(() -> new EntityNotFoundException("Kỹ năng không tồn tại: " + skillName));
    }
    public List<Skill> getSkillsByIds(List<Long> skillIds) {
        return skillRepository.findAllById(skillIds); // Lấy kỹ năng theo ID
    }
    public List<Skill> getSkillsByUserId(Long candidateId) {
        return skillRepository.findSkillsByCandidateId(candidateId);
    }


}

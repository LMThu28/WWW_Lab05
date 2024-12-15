package vn.edu.iuh.fit.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.services.SkillServices;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillResources {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SkillServices skillService;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @PostMapping
    public void saveSkill(@RequestBody Skill skill) {
        skillService.saveSkill(skill);
    }
    @GetMapping("/by-type/{type}")
    public List<Skill> getSkillsByType(@PathVariable SkillType type) {
        return skillService.getSkillsByType(type);
    }
    @GetMapping("/list-all")
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }
    public void createJob(Job job, List<Long> skillIds) {

        jobRepository.save(job);

        List<Skill> skills = skillService.getSkillsByIds(skillIds);

        for (Skill skill : skills) {
            JobSkill jobSkill = new JobSkill();
            jobSkill.setJob(job);
            jobSkill.setSkill(skill);
            jobSkillRepository.save(jobSkill); // Lưu vào cơ sở dữ liệu
        }
    }
}
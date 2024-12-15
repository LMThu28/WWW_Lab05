package vn.edu.iuh.fit.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.services.CandidateServices;

import java.util.List;

@RestController("/api/vi")
public class CandidateResources {
    @Autowired
    private CandidateServices candidateService;
    @GetMapping("/by-skill/{skillId}")
    public List<Candidate> getCandidatesBySkill(@PathVariable Long skillId) {
        return candidateService.getCandidatesBySkillId(skillId);
    }
}

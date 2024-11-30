package vn.edu.iuh.fit.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.fit.dao.ExperienceDao;
import vn.edu.iuh.fit.models.Candidate;

import java.util.List;

@Controller
public class ExpControll {
    private ExperienceDao experienceDao;

    public ExpControll(ExperienceDao experienceDao) {
        this.experienceDao = experienceDao;
    }
    @GetMapping("/no-exp-candidate")
    public String showAddJobForm(Model model) {
        List<Candidate> candidates=experienceDao.getNotHaveExpCandidate();
        model.addAttribute("candidates", candidates);
        return "no-exp-candidate";
    }
}


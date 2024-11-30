package vn.edu.iuh.fit.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.convert.SkillForm;
import vn.edu.iuh.fit.dao.SkillDao;
import vn.edu.iuh.fit.models.Skill;

@Controller
public class SkillControll {
    private SkillDao skillDao;
    @Autowired
    public SkillControll(SkillDao skillDao) {
        this.skillDao = skillDao;
    }
    @GetMapping("/skill")
    public String showAllSkill(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size,
                             Model model) {
        Page<Skill> skills = skillDao.getAll(page, size);
        model.addAttribute("skills", skills.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", skills.getTotalPages());

        return "skill";
    }
    @GetMapping("/delete-skill/{skillID}")
    public String deleteSkill(@PathVariable Long skillID) {
        skillDao.delete(skillID);
        return "redirect:/skill";
    }
    @GetMapping("/active-skill/{skillID}")
    public String activeSkill(@PathVariable Long skillID) {
        skillDao.active(skillID);
        return "redirect:/skill";
    }
    @GetMapping("/update-skill/{skillID}")
    public String showUpdateSkillForm(@PathVariable Long skillID, Model model) {
        Skill skill=skillDao.get(skillID);
        model.addAttribute("skill", skill);
        return "update-skill";
    }
    @PostMapping ("/update-skill")
    public String updateSkill(@ModelAttribute SkillForm skillForm) {
        long skillID=Long.parseLong(skillForm.getSkillID());
        int type=Integer.parseInt(skillForm.getSkillType());
        skillDao.update(new Skill(skillID, skillForm.getSkillName(), skillForm.getSkillDes(), type));
        return "redirect:/skill";
    }
    @GetMapping("/add-skill")
    public String showAddSkillForm() {
        return "add-skill";
    }
    @PostMapping("/add-skill")
    public String addSkill(@ModelAttribute SkillForm skillForm) {
        long skillID=Long.parseLong(skillForm.getSkillID());
        int type=Integer.parseInt(skillForm.getSkillType());
        skillDao.add(new Skill(skillID, skillForm.getSkillName(), skillForm.getSkillDes(), type));
        return "redirect:/skill";
    }
}

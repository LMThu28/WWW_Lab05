package vn.edu.iuh.fit.frontend.controllers;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.backend.services.EmailService;
//import vn.edu.iuh.fit.dhktpm17a.aoconghieu_21026511_lab05.backend.services.EmailService;
import vn.edu.iuh.fit.backend.services.SkillServices;

import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillServices skillService;

    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private EmailService emailService;
    @GetMapping("/list")
    public String showSkillList(Model model) {
        model.addAttribute("skills", skillService.getSkillsByType(SkillType.TECHNICAL_SKILL));
        return "list-all";
    }

    @GetMapping("/create")
    public String showCreateSkillForm(Model model) {
        model.addAttribute("skill", new Skill());
        model.addAttribute("skillTypes", SkillType.values());
        return "skills/create";
    }

    @PostMapping("/create")
    public String createSkill(@ModelAttribute Skill skill) {
        skillService.saveSkill(skill);
        return "redirect:/skills/list-all";
    }

    @GetMapping("/list-all")
    public String showAllSkills(Model model) {
        model.addAttribute("skills", skillService.getAllSkills());
        return "skills/list-all";
    }
    @GetMapping("/viewSkills")
    public ModelAndView viewSkills(@SessionAttribute(value = "candidateLogin", required = false) Candidate candidate) {
        if (candidate == null) {
            return new ModelAndView("redirect:/login");
        }

        List<Skill> skills = skillService.getSkillsByUserId(candidate.getId());

        ModelAndView mav = new ModelAndView("skills/viewSkills");
        mav.addObject("skills", skills);
        return mav;
    }

    @GetMapping("/learnSkills")
    public ModelAndView findSkillLearn(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Candidate can=(Candidate) request.getSession().getAttribute("candidateLogin");
        List<Skill> skills= skillRepository.findSkillCandidateShouldLearn(can.getId());
        modelAndView.addObject("skills", skills);
        modelAndView.setViewName("skills/LearnSkills");
        return modelAndView;
    }

//    @GetMapping("/findCadidateskillMatchOfCompany")
//    public ModelAndView findCandidateSkillMatchOfCompany(HttpServletRequest request) {
//        Company company = (Company) request.getSession().getAttribute("companyLogin");
//        if (company == null) {
//            return new ModelAndView("redirect:/login");
//        }
//
//        List<Skill> skills = skillRepository.findSkillByCompany(company.getId());
//        List<Candidate> candidates = candidateRepository.findAll().subList(0, 10);
//
//        ModelAndView mav = new ModelAndView("company/findCandidateSkillOfCompany");
//        mav.addObject("skills", skills);
//        mav.addObject("candidates", candidates);
//        return mav;
//    }
//
//
//    @PostMapping("/findCandidatewithskill")
//    public ModelAndView findCandidateMatchWithSkill(@RequestParam("skillId") Long skillId, HttpServletRequest request) {
//        Company company = (Company) request.getSession().getAttribute("companyLogin");
//        if (company == null) {
//            return new ModelAndView("redirect:/login");
//        }
//
//        List<Candidate> candidates = candidateRepository.findCandidatesMatchWithSkills(skillId);
//        List<Skill> skills = skillRepository.findSkillByCompany(company.getId());
//
//        ModelAndView mav = new ModelAndView("company/findCandidateSkillOfCompany");
//        mav.addObject("skills", skills);
//        mav.addObject("candidates", candidates);
//        return mav;
//    }
//

}

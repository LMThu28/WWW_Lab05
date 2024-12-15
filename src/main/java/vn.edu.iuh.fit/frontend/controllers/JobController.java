package vn.edu.iuh.fit.frontend.controllers;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.services.CandidateServices;
import vn.edu.iuh.fit.backend.services.CompanyServices;
import vn.edu.iuh.fit.backend.services.InvitationService;
import vn.edu.iuh.fit.backend.services.JobServices;

import java.util.List;
@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobServices jobService;
    @Autowired
    private CandidateServices candidateService;
    @Autowired
    private CompanyServices companyService;
    @Autowired
     CandidateRepository candidateRepository;
    @Autowired
    private JobRepository jobReponsitory;
    @Autowired
    private InvitationService invitationService;
    @GetMapping("/create")
    public String showCreateJobForm(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("companies", companyService.getAllCompanies());
        return "jobs/create";
    }

    @PostMapping("/create")
    public String createJob(@RequestParam Long companyId, @ModelAttribute Job job) {
        Company company = companyService.getCompanyById(companyId);
        job.setCompany(company);
        jobService.saveJob(job);
        return "redirect:/company/job-list";
    }
    @GetMapping("/company-jobs")
public String getJobsByCompany(@SessionAttribute("companyLogin") Company company, Model model) {
    if (company == null) {
        return "redirect:/login";
    }
    List<Job> jobs = jobService.getJobsByCompanyId(company.getId());
    model.addAttribute("jobs", jobs);
    return "company/job-list";
}
    @GetMapping("/search")
    public String searchJobs(@RequestParam("keyword") String keyword, Model model) {
        List<Job> jobs = jobService.searchJobsByKeyword(keyword);
        model.addAttribute("jobs", jobs);
        model.addAttribute("keyword", keyword);
        return "home";
    }
    @GetMapping("/detail/{id}")
    public String findJobDetails(@PathVariable Long id, Model model) {
        // Lấy công việc theo ID
        Job job = jobService.getJobDetails(id);
        if (job == null) {
            return "error";
        }
        model.addAttribute("job", job);

        List<Skill> skills = jobService.getJobSkills(id);
        model.addAttribute("skills", skills);

        List<Candidate> candidates = candidateService.getCandidatesBySkills(skills);
        model.addAttribute("candidates", candidates);
        return "jobs/detail-job";
    }
    @GetMapping("/detail/{jobId}/candidates")
    public String showCandidatesForJob(@PathVariable Long jobId, Model model) {
        List<Candidate> candidates = jobService.findCandidatesMatchWithJob(jobId);
        model.addAttribute("candidates", candidates);
        return "jobs/candidates-list";
    }
    @GetMapping("/findJobWithcan")
    public ModelAndView findJobWithCan(@SessionAttribute(value = "candidateLogin", required = false) Candidate candidate) {
        if (candidate == null) {
            return new ModelAndView("redirect:/login");
        }

        List<Job> listjobs = jobReponsitory.findAllJobMatchWithCandidate(candidate.getId());
        ModelAndView mav = new ModelAndView("jobs/jobWithCan");
        mav.addObject("jobs", listjobs);
        return mav;
    }
    @GetMapping("/details/{id}")
    public ModelAndView jobDetails(@PathVariable Long id) {
        Job job = jobReponsitory.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy công việc với ID: " + id));

        ModelAndView mav = new ModelAndView("jobs/jobDetails");
        mav.addObject("job", job);
        return mav;
    }
    @PostMapping("/apply/{jobId}")
    public String applyForJob(@PathVariable Long jobId, @SessionAttribute("candidateLogin") Candidate candidate, Model model) {
        Job job = jobService.getJobDetails(jobId); // Lấy công việc theo ID
        if (job == null) {
            model.addAttribute("error", "Không tìm thấy công việc.");
            return "error";
        }

        try {
            // Gửi email thông báo ứng tuyển
            invitationService.sendApplicationEmail(job.getCompany().getEmail(), job.getName(), candidate.getFullName());
            model.addAttribute("message", "Đơn ứng tuyển đã được gửi thành công!");
        } catch (MessagingException e) {
            model.addAttribute("error", "Gửi email không thành công: " + e.getMessage());
        }
        return "redirect:/jobs/findJobWithcan";
    }

}

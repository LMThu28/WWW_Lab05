package vn.edu.iuh.fit.frontend.controllers;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.services.*;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobServices jobServices;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private CompanyServices companyServices;
    @Autowired
    private CandidateServices candidateServices;

    @Autowired
    private EmailService emailServices;
    // Trang Dashboard cho công ty
    @GetMapping("/dashboard")
    public String showCompanyDashboard(Model model) {
        model.addAttribute("jobs", jobServices.getAllJobs());
        model.addAttribute("candidates", candidateServices.getAllCandidates());
        return "company/dashboard";
    }
    // Hiển thị form tạo công việc
    @GetMapping("/create-job")
    public String showCreateJobForm(Model model) {
        List<Company> companies = companyServices.getAllCompanies();
        model.addAttribute("companies", companies);
        model.addAttribute("job", new Job());
        return "/company/create-job";
    }

    @PostMapping("/create-job")
    public String createJob(@ModelAttribute Job job, @RequestParam long companyId) {
        Company company = companyServices.getCompanyById(companyId); // Lấy Company từ ID
        job.setCompany(company);
        jobServices.saveJob(job);
        return "redirect:/company/job-list";
    }
    // Trang danh sách công việc
    @GetMapping("/job-list")
    public String showJobList(Model model) {
        model.addAttribute("jobs", jobServices.getAllJobs());
        return "company/job-list";
    }

    // Trang danh sách ứng viên
    @GetMapping("/candidate-list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateServices.getAllCandidates());
        return "company/candidate-list";
    }
    @PostMapping("/send-invitation")
    public String sendInvitation(@RequestParam("candidateEmail") String candidateEmail,
                                 @RequestParam("senderName") String senderName,
                                 @RequestParam("jobTitle") String jobTitle,
                                 @RequestParam("jobLink") String jobLink,
                                 Model model) {
        try {
            // Gửi email mời ứng viên
            emailServices.sendJobInvitationEmail(candidateEmail, senderName, jobTitle, jobLink);
            model.addAttribute("message", "Email mời ứng tuyển đã được gửi đến ứng viên.");
        } catch (MessagingException e) {
            model.addAttribute("message", "Có lỗi khi gửi email mời ứng tuyển.");
        }
        return "redirect:/company/candidate-list";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login"; // Điều hướng đến trang login khi đăng xuất
    }

    @PostMapping("/filterCandidatesBySkill")
    public String filterCandidatesBySkill(@RequestParam("skillName") String skillName, Model model) {
        List<Candidate> candidates = candidateServices.findCandidatesBySkill(skillName);
        model.addAttribute("candidates", candidates);
        return "company/candidate-list";  // Trả về giao diện danh sách ứng viên
    }



}

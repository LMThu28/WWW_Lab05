package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.services.CandidateServices;
import vn.edu.iuh.fit.backend.services.CompanyServices;
@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private CandidateServices candidateService;

    @Autowired
    private CompanyServices companyService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Trả về login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam String role, Model model, HttpServletRequest request) {
        // Xóa các thuộc tính cụ thể trong session
        HttpSession session = request.getSession();
        session.removeAttribute("candidateLogin");
        session.removeAttribute("companyLogin");

        boolean isValid = false;
        if ("candidate".equals(role)) {
            Candidate candidate = candidateService.authenticate(username, password);
            if (candidate != null) {
                session.setAttribute("candidateLogin", candidate);
                model.addAttribute("loggedInUser", candidate.getFullName());
                return "redirect:/candidates/dashboard";
            }
        } else if ("company".equals(role)) {
            Company company = companyService.authenticate(username, password);
            if (company != null) {
                session.setAttribute("companyLogin", company);
                model.addAttribute("loggedInUser", company.getName());
                return "redirect:/company/dashboard";
            }
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
}

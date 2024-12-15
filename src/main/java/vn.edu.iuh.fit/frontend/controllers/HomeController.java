package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.services.JobServices;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private JobServices jobService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "home"; // Trả về home.html
    }
}

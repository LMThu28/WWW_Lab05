package vn.edu.iuh.fit.cotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.dao.CompanyDao;

@Controller
public class CompanyControll {
    private CompanyDao companyDao;
    @Autowired
    public CompanyControll(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }
    @GetMapping("/company-login")
    public String showCompanyLoginForm() {
        return "company-login";
    }
    @PostMapping("/company-login")
    public String processCompanyLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Thực hiện kiểm tra đăng nhập ở đây, ví dụ:
        if (companyDao.logIn(Long.parseLong(username), password)) {
            long compID = Long.parseLong(username);
            return "redirect:/job-manager/" + compID;
        } else {
            model.addAttribute("error", "Thông tin đăng nhập không đúng");
            return "company-login";
        }
    }

}

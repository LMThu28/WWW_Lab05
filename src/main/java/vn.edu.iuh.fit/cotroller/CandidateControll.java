package vn.edu.iuh.fit.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.dao.CandidateDao;

@Controller
public class CandidateControll {
    private final CandidateDao candidateDao;

    @Autowired
    public CandidateControll(CandidateDao candidateDao) {
        this.candidateDao = candidateDao;
    }
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/candidate-login")
    public String showCandidateLoginForm() {
        return "candidate-login";
    }

    @PostMapping("/candidate-login")
    public String processCandidateLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Thực hiện kiểm tra đăng nhập ở đây, ví dụ:
        if (candidateDao.logIn(Long.parseLong(username), password)) {
            long candID = Long.parseLong(username);
            // Đăng nhập thành công, chuyển hướng đến trang job-for-candidate
            return "redirect:/job-for-candidate/" + candID;
        } else {
            // Đăng nhập thất bại, gửi thông báo lỗi và chuyển về trang candidate-login
            model.addAttribute("error", "Thông tin đăng nhập không đúng");
            return "candidate-login";
        }
    }


}

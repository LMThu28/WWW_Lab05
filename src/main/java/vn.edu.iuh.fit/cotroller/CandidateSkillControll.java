package vn.edu.iuh.fit.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.iuh.fit.dao.CandidateSkillDao;
import vn.edu.iuh.fit.dao.JobSkillDao;
import vn.edu.iuh.fit.models.Candidate;
import vn.edu.iuh.fit.models.Job;
import vn.edu.iuh.fit.models.Skill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CandidateSkillControll {
    private final CandidateSkillDao candidateSkillDao;
    private final JobSkillDao jobSkillDao;

    @Autowired
    public CandidateSkillControll(CandidateSkillDao candidateSkillDao, JobSkillDao jobSkillDao) {
        this.candidateSkillDao = candidateSkillDao;
        this.jobSkillDao = jobSkillDao;
    }

    @GetMapping("/job-for-candidate/{candID}")
    public String showJobForCandidate(@PathVariable Long candID, Model model) {
        // Sử dụng CandidateSkillDao để lấy danh sách công việc phù hợp với ứng viên
        List<Job> jobs = candidateSkillDao.getJobForCandidateOrderBySkil(candID);
        Map<Job, Double> map=new HashMap<>();
        // Lặp qua danh sách công việc và lấy giá trị price cho mỗi công việc
        for (Job job : jobs) {
            double price=jobSkillDao.calcProposedSalary(job.getJob_id());
            map.put(job, price);
        }
        // Đặt danh sách công việc vào model để hiển thị trong giao diện người dùng
        model.addAttribute("mapJob", map);
        model.addAttribute("candID", candID);
        return "job-for-candidate";
    }
    @GetMapping("/skill-to-learn/{candID}")
    public String showSkillToLearn(@PathVariable Long candID, Model model) {
        List<Skill> skills = candidateSkillDao.getSkillNotHaveToLearn(candID);
        model.addAttribute("skills", skills);
        model.addAttribute("candID", candID);
        return "skill-to-learn";
    }
    @GetMapping("/cand-by-skill/{skillID}")
    public String showCandBySkill(@PathVariable Long skillID, Model model) {
        List<Candidate> candidates=candidateSkillDao.getCandidateOrderBySkill(skillID);
        model.addAttribute("candidates", candidates);
        model.addAttribute("skillID", skillID);
        return "cand-by-skill";
    }
}

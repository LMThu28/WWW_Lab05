package vn.edu.iuh.fit.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.dao.JobDao;
import vn.edu.iuh.fit.dao.SkillDao;
import vn.edu.iuh.fit.models.Job;
import vn.edu.iuh.fit.models.Skill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class JobControll {
    private JobDao jobDao;
    private SkillDao skillDao;

    @Autowired
    public JobControll(JobDao jobDao, SkillDao skillDao) {
        this.jobDao = jobDao;
        this.skillDao=skillDao;
    }
    @GetMapping("/job-manager/{compID}")
    public String showAllJob(@PathVariable Long compID,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size,
                             Model model) {
        Page<Job> jobs = jobDao.getAll(page, size);

        Map<Job, List<Skill>> map = new HashMap<>();
        for (Job job : jobs.getContent()) {
            List<Skill> skills = skillDao.getSkillsForJob(job.getJob_id());
            map.put(job, skills);
        }

        model.addAttribute("mapJob", map);
        model.addAttribute("compID", compID);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", jobs.getTotalPages());

        return "job-manager";
    }
    @GetMapping("/add-job/{compID}")
    public String showAddJobForm(@PathVariable Long compID, Model model) {
        List<Skill> skills=skillDao.getAllSkillList();
        model.addAttribute("compID", compID);
        model.addAttribute("skills", skills);
        return "add-job";
    }



}

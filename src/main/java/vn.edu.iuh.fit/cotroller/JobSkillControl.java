package vn.edu.iuh.fit.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.iuh.fit.convert.JobForm;
import vn.edu.iuh.fit.dao.JobSkillDao;
import vn.edu.iuh.fit.models.Company;
import vn.edu.iuh.fit.models.Job;
import vn.edu.iuh.fit.models.JobSkill;
import vn.edu.iuh.fit.models.Skill;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JobSkillControl {
    private final JobSkillDao jobSkillDao;

    @Autowired
    public JobSkillControl(JobSkillDao jobSkillDao) {
        this.jobSkillDao = jobSkillDao;
    }
    @PostMapping("/add-job")
    public String addJob(@ModelAttribute JobForm jobForm, Model model) {
        long compID= Long.parseLong(jobForm.getCompID());
        long jobID= Long.parseLong(jobForm.getJobID());
        int jobLevel=Integer.parseInt(jobForm.getSkillLevel());
        //Xử lý mảng skill
        List<String> selectedSkills = jobForm.getSkills();
        List<JobSkill> jobSkills=new ArrayList<>();
        for (String s:selectedSkills) {
            long skillID=Long.parseLong(s);
            jobSkills.add(new JobSkill(new Skill(skillID), jobForm.getMoreInfo(), jobLevel));
        }
        jobSkillDao.addJobSkill(new Job(jobID, jobForm.getJobName(), jobForm.getJobDes(), new Company(compID)), jobSkills);

        return "redirect:/job-manager/"+ compID;
    }
}

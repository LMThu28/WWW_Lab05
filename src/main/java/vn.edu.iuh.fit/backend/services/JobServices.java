package vn.edu.iuh.fit.backend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;

import java.util.List;

@Service
public class JobServices {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private SkillServices skillService;

    public void saveJob(Job job) {
        jobRepository.save(job);
    }

    public List<Job> getJobsBySkill(Long skillId) {
        return jobRepository.findBySkillId(skillId);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll(); // Truy vấn tất cả công việc
    }

    public List<Job> searchJobsByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return jobRepository.findAll(); // Trả về tất cả công việc nếu từ khóa trống
        }
        return jobRepository.findByTitleContainingIgnoreCase(keyword.trim());
    }
    public List<Job> searchJobs(String keyword) {
        return jobRepository.findAll().stream()
                .filter(job -> job.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
    public Job getJobById(Long jobId) throws EntityNotFoundException {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));
    }

    public Job getJobDetails(Long jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }

    public List<Skill> getJobSkills(Long jobId) {
        return jobRepository.findSkillsByJobId(jobId);
    }
    public void createJob(Job job, List<Long> skillIds) {
        jobRepository.save(job);

        List<Skill> skills = skillService.getSkillsByIds(skillIds);

        for (Skill skill : skills) {
            JobSkill jobSkill = new JobSkill();
            jobSkill.setJob(job);
            jobSkill.setSkill(skill);
            jobSkillRepository.save(jobSkill); // Lưu vào cơ sở dữ liệu
        }

    }
    public List<Candidate> findCandidatesMatchWithJob(Long jobId) {
    Job job = jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
    List<Skill> skills = jobRepository.findSkillsByJobId(jobId);
    return candidateRepository.findCandidatesBySkillsAndLocation(skills, job.getCompany().getAddress().getCity());
}
    public List<Job> getJobsByCompanyId(Long companyId) {
        return jobRepository.findByCompanyId(companyId);
    }
}


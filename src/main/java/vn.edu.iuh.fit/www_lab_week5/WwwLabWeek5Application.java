package vn.edu.iuh.fit.www_lab_week5;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import vn.edu.iuh.fit.dao.*;
import vn.edu.iuh.fit.models.JobSkill;
import vn.edu.iuh.fit.models.Skill;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WwwLabWeek5Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(WwwLabWeek5Application.class, args);
		CandidateDao candidateDao = context.getBean(CandidateDao.class);
/*
        ---------------------chạy candidatedao:-----------------------
*/
  //      System.out.println(candidateDao.logIn(2,"987-654-3210"));
       /* candidateDao.getCandByYearBorn().entrySet().forEach(entry ->{
            System.out.println(entry.getKey() + "\n So luong:"+ entry.getValue());
        });*/
        /*candidateDao.getTotalSkillByCand().entrySet().forEach(entry ->{
            System.out.println(entry.getKey() + "\n So luong:"+ entry.getValue());
        });*/
        /*candidateDao.getTotalJobSkillByCand().entrySet().forEach(entry ->{
            System.out.println(entry.getKey() + "\n So luong:"+ entry.getValue());
        });*/

/*
        ---------------------chạy candidateskilldao:-----------------------
*/
		CandidateSkillDao candidateSkillDao=context.getBean(CandidateSkillDao.class);
        /*candidateSkillDao.getJobForCandidateOrderBySkil(2).
                forEach(j->System.out.println(j.toString()));*/
       /* candidateSkillDao.getSkillNotHaveToLearn(2).
                forEach(j->System.out.println(j.toString()));*/
        /*candidateSkillDao.getCandidateOrderBySkill(2).
                forEach(j->System.out.println(j.toString()));*/

/*
        ---------------------chạy companydao:-----------------------
*/
		CompanyDao companyDao =context.getBean(CompanyDao.class);
		System.out.println(companyDao.logIn(3, "555-123-4567"));
/*
        ---------------------chạy experiencedao:-----------------------
*/
		ExperienceDao experienceDao =context.getBean(ExperienceDao.class);
//        experienceDao.getMaxExpYearCandidate().forEach(c->System.out.println(c.toString()));
//        experienceDao.getNotHaveExpCandidate().forEach(c->System.out.println(c.toString()));
/*
        ---------------------chạy jobdao:-----------------------
*/
		JobDao jobDao =context.getBean(JobDao.class);
//        jobDao.getAll().forEach(j->System.out.println(j.toString()));
/*
        ---------------------chạy jobskillDAO:-----------------------
*/
		JobSkillDao jobSkillDao =context.getBean(JobSkillDao.class);
		List<JobSkill> jobSkills= new ArrayList<>();
		jobSkills.add(new JobSkill(new Skill(6),"Quality assurance expertise is essential for this role",4 ));
		jobSkills.add(new JobSkill(new Skill(7),"Financial analysis skills and reporting are a requirement",3 ));
//        System.out.println(jobSkillDao.addJobSkill(new Job(35, "Quality Assurance Engineer", "Ensure the quality of software products.", new Company(3)),jobSkills ));
//        System.out.println(jobSkillDao.calcProposedSalary(30L));
/*
        ---------------------chạy skillDAO:-----------------------
*/
		SkillDao skillDao =context.getBean(SkillDao.class);
//        System.out.println(skillDao.get(2));
//        skillDao.getAll().forEach(s->System.out.println(s.toString()));
		System.out.println(skillDao.delete(30));



//        context.close();

	}

}


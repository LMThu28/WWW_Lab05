package vn.edu.iuh.fit.convert;

import java.util.List;

public class JobForm {
    private String compID;
    private String jobID;
    private String jobName;
    private String jobDes;
    private List<String> skills;
    private String skillLevel;
    private String moreInfo;

    public JobForm() {
    }

    public JobForm(String compID, String jobID, String jobName, String jobDes, List<String> skills, String skillLevel, String moreInfo) {
        this.compID = compID;
        this.jobID = jobID;
        this.jobName = jobName;
        this.jobDes = jobDes;
        this.skills = skills;
        this.skillLevel = skillLevel;
        this.moreInfo = moreInfo;
    }

    public String getCompID() {
        return compID;
    }

    public void setCompID(String compID) {
        this.compID = compID;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDes() {
        return jobDes;
    }

    public void setJobDes(String jobDes) {
        this.jobDes = jobDes;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return "JobForm{" +
                "compID='" + compID + '\'' +
                ", jobID='" + jobID + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobDes='" + jobDes + '\'' +
                ", skills=" + skills +
                ", skillLevel='" + skillLevel + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                '}';
    }
}

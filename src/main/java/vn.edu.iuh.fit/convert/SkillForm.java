package vn.edu.iuh.fit.convert;

public class SkillForm {
    private String skillID;
    private String skillName;
    private String skillDes;
    private String skillType;

    public SkillForm() {
    }

    public SkillForm(String skillID, String skillName, String skillDes, String skillType) {
        this.skillID = skillID;
        this.skillName = skillName;
        this.skillDes = skillDes;
        this.skillType = skillType;
    }

    public String getSkillID() {
        return skillID;
    }

    public void setSkillID(String skillID) {
        this.skillID = skillID;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDes() {
        return skillDes;
    }

    public void setSkillDes(String skillDes) {
        this.skillDes = skillDes;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    @Override
    public String toString() {
        return "SkillForm{" +
                "skillID='" + skillID + '\'' +
                ", skillName='" + skillName + '\'' +
                ", skillDes='" + skillDes + '\'' +
                ", skillType='" + skillType + '\'' +
                '}';
    }
}

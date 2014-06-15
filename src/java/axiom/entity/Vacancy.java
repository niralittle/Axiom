package axiom.entity;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class Vacancy {

    Date date;
    int startupID;
    String name;
    int id;
    String description;
    ArrayList<Skill> skills;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStartupID(int startupId) {
        this.startupID = startupId;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    } 

    public Vacancy() { 
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vacancy(Date date, int startupId, Skill skill) {
        this.date = date;
        this.startupID = startupId;
        this.skills = new ArrayList<Skill>();
        this.skills.add(skill);
    }

    public List<Skill> getSkillsId() {
        return skills;
    }

    public int getStartupId() {
        return startupID;
    }

    public void addSkill(Skill skill)
    {
        this.skills.add(skill);
    }

    public Date getDate() {
        return date;
    }

}
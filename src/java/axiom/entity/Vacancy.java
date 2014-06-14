package axiom.entity;

import java.util.ArrayList;
import java.util.List;
import sun.util.calendar.LocalGregorianCalendar.Date;
import axiom.entity.Skill;

public class Vacancy{
    Date date;
    int startupId;
    ArrayList<Skill> skills;
    boolean free;


    public Vacancy(Date date, int startupId, Skill skill) {
        this.date = date;
        this.startupId = startupId;
        this.skills = new ArrayList<Skill>();
        this.skills.add(skill);
        this.free = true;
    }

    public List<Skill> getSkillsId() {
        return skills;
    }

    public int getStartupId() {
        return startupId;
    }

    public void addSkill(Skill skill)
    {
        this.skills.add(skill);
    }

    public Date getDate() {
        return date;
    }

    boolean isFree() {return this.free;}

    void Vacancy(){this.free = false;}
}
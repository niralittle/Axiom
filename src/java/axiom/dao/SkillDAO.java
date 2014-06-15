package axiom.dao;

import axiom.entity.Skill;
import java.util.List;
import java.util.Map;
import axiom.dbmanager.DBManagerException;

/**
 *
 * @author Nira
 */
public interface SkillDAO extends GenericDAO<Skill> {

    public List<Skill> getAllSkills(int offset,
            int numberOfRecords) throws DBManagerException;
    
    public Map<String, String> getUserSkills(int userID, int offset,
            int numberOfRecords) throws DBManagerException;

    public int getSkillIDByName(String name) throws DBManagerException;

}

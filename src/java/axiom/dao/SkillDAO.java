package axiom.dao;

import axiom.entity.Skill;
import axiom.entity.User;
import java.util.List;
import axiom.dbmanager.DBManagerException;

/**
 *
 * @author Nira
 */
public interface SkillDAO extends GenericDAO<Skill> {

    public List<Skill> getAllSkill() throws DBManagerException;

    public List<Skill> getUserSkill(User user) throws DBManagerException;

}

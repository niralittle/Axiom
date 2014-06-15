package axiom.dao.impl;

import axiom.dao.SkillDAO; 
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.Skill;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nira
 */
public class SkillDAOImpl extends GenericDAOImpl<Skill> implements SkillDAO {

    public SkillDAOImpl(DBManager dbManager) {
        super(dbManager);
    }
    public List<Skill> getAllSkills(int offset, int numberOfRecords)
            throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
            throw new DBManagerException("Illegal argument in paging - " +
                    "less than 1. Can't proccess the request!");
    	}
    	Statement statement = null;
    	List<Skill> skills = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM SKILL s " +
                        "ORDER BY s.name " +
                        ") a where ROWNUM <= ? ) " +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, offset + numberOfRecords);
            statement.setInt(2, offset);

            ri = statement.executeQuery();
            skills = new ArrayList<Skill>();

            while (ri.next()) {
                Skill s = new Skill();
                s.setId(ri.getInt("id"));
                s.setName(ri.getString("name"));
                skills.add(s);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "contact the administrator");
        } finally {
            statement.close();
        }
        return skills;
    }

    public Map<String, String> getUserSkills(int userID, int offset, 
            int numberOfRecords) throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
            throw new DBManagerException("Illegal argument in paging - " +
                    "less than 1. Can't proccess the request!");
    	}
    	Statement statement = null;
    	ResultIterator ri  = null;
        Map<String, String> map = new HashMap<String, String>();
    	String query =  "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM ( " +
                        "SELECT s.PROOF proof, sk.NAME name " +
                        "FROM USERSKILL s " +
                        "WHERE s.USERID = ? " +
                        "JOIN SKILL sk " +
                        "ON sk.ID = s.SKILLID " +
                        "ORDER BY s.name " +
                        ") a where ROWNUM <= ? ) " +
                        "WHERE rnum  > ? ";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setInt(2, offset + numberOfRecords);
            statement.setInt(3, offset);

            ri = statement.executeQuery();

            while (ri.next()) {
                String name = ri.getString("name");
                String proof = ri.getString("proof");
                map.put(name, proof);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "contact the administrator");
        } finally {
            statement.close();
        }
        return map;
    }

    public int getSkillIDByName(String name) throws DBManagerException {
        if (name == null) {
    		throw new DBManagerException("Passed parameter <name> is null" +
                        " or empty. Can't proccess the request!");
    	}
    	Statement statement = null;
    	ResultIterator ri  = null;
        int skillID;
    	String query  = "SELECT * " +
                        "FROM SKILL s " +
                        "WHERE s.name = ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setString(1, (name));
            ri = statement.executeQuery();
            skillID = ri.getInt("id");
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "please, contact an administrator");
        } finally {
            statement.close();
        }
        return skillID;
    }
}

package axiom.dao.impl;

import axiom.dao.ParticipantDAO;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.Participant;
import axiom.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nira
 */
public class ParticipantDAOImpl extends GenericDAOImpl<Participant>
                                        implements ParticipantDAO {

    public ParticipantDAOImpl(DBManager dbm) {
        super(dbm);
    }

    public List<User> getParticipants(int startupID) throws DBManagerException {

    	Statement statement = null;
    	List<User> users = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT u.* " +
                        "FROM USER u, PARTICIPANT p " +
                        "WHERE p.STARTUPID = ? " +
                        "AND p.USERID = u.ID " +
                        "ORDER BY u.LASTNAME ";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, startupID);

            ri = statement.executeQuery();
            users = new ArrayList<User>();

            while (ri.next()) {
                User u = new User();
                u.setId(ri.getInt("id"));
                u.setFirstName(ri.getString("FIRSTNAME"));
                u.setLastName(ri.getString("LASTDNAME")); 
                u.setAvatarID(ri.getInt("AVATARID"));
                users.add(u);
            }
        } catch (DBManagerException exc) {
                throw new DBManagerException ("An error occured, " +
                                "contact the administrator");
        } finally {
            statement.close();
        }
        return users;
    }

    public void addParticipantToStartup(int startupID, int userID, String role)
            throws DBManagerException {

    	Statement statement = null;
     	ResultIterator ri  = null;
    	String query  = "INSERT INTO PARTICIPANT(USERID, STARTUPID, ROLE) " +
                        "VALUES ( ?, ?, ? )";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setInt(2, startupID);
            statement.setString(3, role);

            ri = statement.executeQuery();

        } catch (DBManagerException exc) {
                throw new DBManagerException ("An error occured, " +
                                "contact the administrator");
        } finally {
            statement.close();
        }
    }

    public void deleteParticipantFromStartup(int startupID, int userID)
            throws DBManagerException {
    	Statement statement = null;
     	ResultIterator ri  = null;
    	String query  = "DELETE FROM PARTICIPANT " +
                        "WHERE STARTUPID = ? " +
                        "AND USERID = ? "; 
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(2, userID);
            statement.setInt(1, startupID); 

            ri = statement.executeQuery();
 
        } catch (DBManagerException exc) {
                throw new DBManagerException ("An error occured, " +
                                "contact the administrator");
        } finally {
            statement.close();
        }
    }

}

package axiom.dao.impl;

import axiom.dao.*;
import axiom.dao.impl.GenericDAOImpl;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.ProfileState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nira
 */
public class ProfileStateDAOImpl extends GenericDAOImpl<ProfileState>
                        implements ProfileStateDAO {
    public ProfileStateDAOImpl(DBManager dbm) {
        super(dbm);
    }

    public List<ProfileState> getAllProfileStates() throws DBManagerException {

    	Statement statement = null;
    	List<ProfileState> li = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * " +
                        "FROM PROFILESTATE ";
        try {
            statement = dbManager.prepareStatement(query);

            ri = statement.executeQuery();
            li = new ArrayList<ProfileState>();

            while (ri.next()) {
                ProfileState p = new ProfileState();
                p.setId(ri.getInt("id"));
                p.setDescription(ri.getString("DESCRIPTION"));
                li.add(p);
            }
        } catch (DBManagerException exc) {
                throw new DBManagerException ("An error occured, " +
                                "contact the administrator");
        } finally {
                statement.close();
        }
        return li;
    }

    public ProfileState getProfilestateByDescription(String description)
            throws DBManagerException {

    	Statement statement = null;
    	ProfileState p = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * " +
                        "FROM PROFILESTATE " +
                        "WHERE DESCRIPTION = ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setString(1, description);
            ri = statement.executeQuery();
            p.setId(ri.getInt("id"));
            p.setDescription(description); 

        } catch (DBManagerException exc) {
                throw new DBManagerException ("An error occured, " +
                                "contact the administrator");
        } finally {
                statement.close();
        }
        return p;
    }

}

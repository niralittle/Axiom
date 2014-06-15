package axiom.dao.impl;

import axiom.dao.StartupStateDAO;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.StartupState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nira
 */
public class StartupStateDAOImpl extends GenericDAOImpl<StartupState>
        implements StartupStateDAO{

    public StartupStateDAOImpl(DBManager dbManager) {
        super(dbManager);
    }

    public List<StartupState> getAllStartupStates() throws DBManagerException {
    	Statement statement = null;
    	List<StartupState> sStates;
    	ResultIterator ri  = null;
    	String query  = "SELECT * " +
                        "FROM STARTUPSTATE " ;
        try {
            statement = dbManager.prepareStatement(query);
            ri = statement.executeQuery();
            sStates = new ArrayList<StartupState>();
            StartupState state;
            while (ri.next()) {
                state = new StartupState();
                state.setId(ri.getInt("ID"));
                state.setDescription(ri.getString("DESCRIPTION"));
                sStates.add(state);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return sStates;
    }    

    public StartupState getStartupStateByDescription(String description)
            throws DBManagerException {
        if (description == null) {
    		throw new DBManagerException("Passed parameter <name> " +
                        "is null or empty. Can't proccess the request!");
    	}
        Statement statement = null;
    	ResultIterator ri  = null;
        StartupState state;
    	String query  = "SELECT * " +
                        "FROM STARTUPSTATE s " +
                        "WHERE s.DESCRIPTION = ? ";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setString(1, description);
            ri = statement.executeQuery();
            state = new StartupState();
            state.setId(ri.getInt("ID"));
            state.setDescription(ri.getString("DESCRIPTION"));

        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return state;
    }

}

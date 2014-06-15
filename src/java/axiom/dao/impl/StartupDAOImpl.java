package axiom.dao.impl;

import axiom.dao.StartupDAO;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.Startup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nira
 */
public class StartupDAOImpl extends GenericDAOImpl<Startup> implements StartupDAO {

    public StartupDAOImpl(DBManager dbManager) {
        super(dbManager);
    }
    public List<Startup> getStartupsByName(String name, int offset, int numberOfRecords)
            throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
    		throw new DBManagerException("Illegal argument in paging - less than 1. "
    				+ " Can't proccess the request!");
    	}
        if (name == null) {
    		throw new DBManagerException("Passed parameter <name> is null or empty"
    				+ " Can't proccess the request!");
    	}
    	Statement statement = null;
    	List<Startup> li;
    	ResultIterator ri  = null;
    	String query  ="SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM STARTUP s " +
                        "WHERE f.name = ? " +
                        "ORDER BY s.name " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
		try {
                    statement = dbManager.prepareStatement(query);
                    statement.setString(1, (name));
                    statement.setInt(2, offset + numberOfRecords);
                    statement.setInt(3, offset);
                    ri = statement.executeQuery();

                    li = new ArrayList<Startup>();
                    Startup s;
                    while (ri.next()) {
                        s = new Startup();
                        s.setId(ri.getInt("ID"));
                        s.setName(ri.getString("NAME"));
                        s.setOwnerId(ri.getInt("OWNERID"));
                        s.setProjectType(ri.getInt("PROJECTTYPE"));
                        s.setDescription(ri.getString("DESCRIPTION"));
                        s.setStartupStateID(ri.getInt("STARTUPSTATEID"));
                        li.add(s);
                    }
		} catch (DBManagerException exc) {
                    throw new DBManagerException ("An error occured, " +
                                    "pleaase, contact an administrator");
		} finally {
                    statement.close();
		}
        return li;
    }

    public List<Startup> getAllStartups(int offset, int numberOfRecords)
            throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
    		throw new DBManagerException("Illegal argument in paging - less than 1. "
    				+ " Can't proccess the request!");
    	}
        Statement statement = null;
    	List<Startup> li;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM STARTUP s " +
                        "ORDER BY s.name " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, offset + numberOfRecords);
            statement.setInt(2, offset);
            ri = statement.executeQuery();

            li = new ArrayList<Startup>();
            Startup s;
            while (ri.next()) {
                s = new Startup();
                s.setId(ri.getInt("ID"));
                s.setName(ri.getString("NAME"));
                s.setOwnerId(ri.getInt("OWNERID"));
                s.setProjectType(ri.getInt("PROJECTTYPE"));
                s.setDescription(ri.getString("DESCRIPTION"));
                s.setStartupStateID(ri.getInt("STARTUPSTATEID"));
                li.add(s);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return li;
    }

    public List<Startup> getStartupsByProjectType(int typeID, int offset, int numberOfRecords)
            throws DBManagerException {
         if (numberOfRecords < 1 || offset < 0) {
    		throw new DBManagerException("Illegal argument in paging - less than 1. "
    				+ " Can't proccess the request!");
    	}
    	Statement statement = null;
    	List<Startup> li;
    	ResultIterator ri  = null;
    	String query  ="SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM STARTUP s " +
                        "WHERE f.PROJECTTYPE = ? " +
                        "ORDER BY s.name " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, (typeID));
            statement.setInt(2, offset + numberOfRecords);
            statement.setInt(3, offset);
            ri = statement.executeQuery();

            li = new ArrayList<Startup>();
            Startup s;
            while (ri.next()) {
                s = new Startup();
                s.setId(ri.getInt("ID"));
                s.setName(ri.getString("NAME"));
                s.setOwnerId(ri.getInt("OWNERID"));
                s.setProjectType(ri.getInt("PROJECTTYPE"));
                s.setDescription(ri.getString("DESCRIPTION"));
                s.setStartupStateID(ri.getInt("STARTUPSTATEID"));
                li.add(s);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return li;
    }

    public List<Startup> getStartupsByState(int stateID, int offset, int numberOfRecords)
            throws DBManagerException {
         if (numberOfRecords < 1 || offset < 0) {
    		throw new DBManagerException("Illegal argument in paging - less than 1. "
    				+ " Can't proccess the request!");
    	}
    	Statement statement = null;
    	List<Startup> li;
    	ResultIterator ri  = null;
    	String query  ="SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM STARTUP s " +
                        "WHERE f.STARTUPSTATEID = ? " +
                        "ORDER BY s.NAME " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, (stateID));
            statement.setInt(2, offset + numberOfRecords);
            statement.setInt(3, offset);
            ri = statement.executeQuery();

            li = new ArrayList<Startup>();
            Startup s;
            while (ri.next()) {
                s = new Startup();
                s.setId(ri.getInt("ID"));
                s.setName(ri.getString("NAME"));
                s.setOwnerId(ri.getInt("OWNERID"));
                s.setProjectType(ri.getInt("PROJECTTYPE"));
                s.setDescription(ri.getString("DESCRIPTION"));
                s.setStartupStateID(ri.getInt("STARTUPSTATEID"));
                li.add(s);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return li;
    }

}

package axiom.dao.impl;

import axiom.dao.FacultyDAO;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.Faculty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nira
 */
public class FacultyDAOImpl extends GenericDAOImpl<Faculty> implements FacultyDAO {

    public FacultyDAOImpl(DBManager dbManager) {
        super(dbManager);
    }

    public Faculty getFacultyByName(String name) throws DBManagerException {
        if (name == null) {
    		throw new DBManagerException("Passed parameter <name> is null or empty"
    				+ " Can't proccess the request!");
    	}
    	Statement statement = null;
    	Faculty f;
    	ResultIterator ri  = null;
    	String query  = "SELECT * " +
                        "FROM FACULTY f " +
                        "WHERE f.name = ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setString(1, (name));
            ri = statement.executeQuery();

            f = new Faculty();
            f.setId(ri.getInt("id"));
            f.setName(ri.getString("name"));
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return f;
    }

    public List<Faculty> getAllFaculties(int offset, int numberOfRecords)
            throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
    		throw new DBManagerException("Illegal argument in paging - less than 1. "
    				+ " Can't proccess the request!");
    	}
    	Statement statement = null;
    	List<Faculty> facs = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM FACULTY f " +
                        "ORDER BY f.name " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
                statement = dbManager.prepareStatement(query);
                statement.setInt(1, offset + numberOfRecords);
                statement.setInt(2, offset);

                ri = statement.executeQuery();
                facs = new ArrayList<Faculty>();

                while (ri.next()) {
                        Faculty f = new Faculty();
                        f.setId(ri.getInt("id"));
                        f.setName(ri.getString("name"));
                        facs.add(f);
                }
        } catch (DBManagerException exc) {
                throw new DBManagerException ("An error occured, " +
                                "contact the administrator");
        } finally {
                statement.close();
        }
        return facs;
    }
}

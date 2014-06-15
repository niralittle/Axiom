package axiom.dao.impl;

import axiom.dao.MajorDAO;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.Major;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nira
 */
public class MajorDAOImpl extends GenericDAOImpl<Major> implements MajorDAO {

    public MajorDAOImpl(DBManager dbManager) {
        super(dbManager);
    }

    public Major getMajorByName(String name) throws DBManagerException {
      if (name == null) {
    		throw new DBManagerException("Passed parameter <name> is null" +
                        " or empty. Can't proccess the request!");
    	}
    	Statement statement = null;
    	Major m;
    	ResultIterator ri  = null;
    	String query  = "SELECT * " +
                        "FROM MAJOR m " +
                        "WHERE m.name = ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setString(1, (name));
            ri = statement.executeQuery();

            m = new Major();
            m.setId(ri.getInt("id"));
            m.setName(ri.getString("name"));
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return m;
    }

    public List<Major> getAllMajors(int offset, int numberOfRecords)
            throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
            throw new DBManagerException("Illegal argument in paging - less than 1. "
                            + " Can't proccess the request!");
    	}
    	Statement statement = null;
    	List<Major> majs = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM MAJOR m " +
                        "ORDER BY m.name " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, offset + numberOfRecords);
            statement.setInt(2, offset);

            ri = statement.executeQuery();
            majs = new ArrayList<Major>();

            while (ri.next()) {
                Major m = new Major();
                m.setId(ri.getInt("id"));
                m.setName(ri.getString("name"));
                majs.add(m);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "contact the administrator");
        } finally {
            statement.close();
        }
        return majs;
    }
}

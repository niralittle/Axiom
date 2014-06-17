 package axiom.dao.impl;

import axiom.dao.PostDAO;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.Post;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nira
 */
public class PostDAOImpl extends GenericDAOImpl<Post> implements PostDAO {

    public PostDAOImpl(DBManager dbm) {
        super(dbm);
    }

    public List<Post> getUsersPosts(int userID, int offset, int numberOfRecords)
            throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
    		throw new DBManagerException("Illegal argument in paging - less than 1. "
    				+ " Can't proccess the request!");
    	}
        Statement statement = null;
    	List<Post> li;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM POST s " +
                        "WHERE s.USERID = ? " +
                        "ORDER BY s.DATE " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setInt(2, offset + numberOfRecords);
            statement.setInt(3, offset);
            ri = statement.executeQuery();

            li = new ArrayList<Post>();
            Post s;
            while (ri.next()) {
                s = new Post();
                s.setUserID(ri.getInt("ID"));
                s.setName(ri.getString("NAME"));
                s.setDate(ri.getDate("DATE"));
                s.setContent(ri.getString("CONTENT")); 
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

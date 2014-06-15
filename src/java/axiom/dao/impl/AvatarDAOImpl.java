package axiom.dao.impl;

import axiom.dao.AvatarDAO;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.Avatar;
import java.sql.Blob;

/**
 *
 * @author Nira
 */
public class AvatarDAOImpl extends GenericDAOImpl<Avatar> 
        implements AvatarDAO {
    
    public AvatarDAOImpl(DBManager dbManager) {
        super(dbManager);
    }
    
    public Avatar getUsersAvatar(int userID) throws DBManagerException {
 
    	Statement statement = null;
    	Avatar a;
    	ResultIterator ri  = null;
    	String query =  "SELECT * " +
                        "FROM AVATAR a " +
                        "WHERE a.USERID = ? ";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, (userID)); 
            ri = statement.executeQuery();

            a = new Avatar();
            a.setId(ri.getInt("ID"));
            a.setFileName(ri.getString("NAME"));
            a.setImage(ri.getBlob("IMAGE"));
             
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "please, contact an administrator");
        } finally {
            statement.close();
        }
        return a;
    }

    public boolean setUsersAvatar(int userID, Avatar a) throws DBManagerException {
        
        Statement statement = null;
     	ResultIterator ri  = null;
    	String query =  "INSERT INTO AVATAR(NAME, IMAGE)  " +
                        "VALUES ( ? , ? ) " ;
        try {
            statement = dbManager.prepareStatement(query);
            statement.setString(1, (a.getFileName()));
            statement.setBlob(2, a.getImage());
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "please, contact an administrator");
        } finally {
            statement.close();
        }
        return true;
    }

}

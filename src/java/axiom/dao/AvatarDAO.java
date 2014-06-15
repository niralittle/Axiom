package axiom.dao;

import axiom.entity.Avatar;
import axiom.dbmanager.DBManagerException;

/**
 *
 * @author user
 */

public interface AvatarDAO extends GenericDAO<Avatar>{

    public Avatar getUsersAvatar(int userID) throws DBManagerException;

    public boolean setUsersAvatar(int userID, Avatar a) throws DBManagerException;

}

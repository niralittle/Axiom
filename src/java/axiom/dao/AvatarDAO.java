package axiom.dao;

import axiom.entity.Avatar;
import axiom.entity.User;
import axiom.dbmanager.DBManagerException;

/**
 *
 * @author user
 */
public interface AvatarDAO extends GenericDAO<Avatar>{

    public Avatar getAvatarForUser(User user) throws DBManagerException;

    public Avatar getAvatarById(int id) throws DBManagerException;

}

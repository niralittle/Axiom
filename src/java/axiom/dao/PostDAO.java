 package axiom.dao;

import axiom.dbmanager.DBManagerException;
import axiom.entity.Post;
import java.util.List;

/**
 *
 * @author Nira
 */
public interface PostDAO extends GenericDAO<Post> {

    public List<Post> getUsersPosts(int userID, int offset, int numberOfRecords)
            throws DBManagerException;
}

package axiom.dao;

import axiom.entity.Profilestate;
import java.util.List;
import axiom.dbmanager.DBManagerException;

/**
 *
 * @author user
 */
public interface ProfilestateDAO extends GenericDAO {

    public List<Profilestate> getAllProfilestates() throws DBManagerException;

    public Profilestate getProfilestateById() throws DBManagerException;

    public Profilestate getProfilestateByDescription(String description) throws DBManagerException;
}

package axiom.dao;

import axiom.entity.ProfileState;
import java.util.List;
import axiom.dbmanager.DBManagerException;

/**
 *
 * @author user
 */
public interface ProfileStateDAO extends GenericDAO<ProfileState> {

    public List<ProfileState> getAllProfileStates() throws DBManagerException;

    public ProfileState getProfilestateByDescription(String description)
            throws DBManagerException;
}

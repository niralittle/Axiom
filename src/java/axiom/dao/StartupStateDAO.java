package axiom.dao;

import axiom.entity.StartupState;
import java.util.List;
import axiom.dbmanager.DBManagerException;
/**
 *
 * @author Nira
 */
public interface StartupStateDAO extends GenericDAO<StartupState>{

    public List<StartupState> getAllStartupStates() throws DBManagerException;

    public StartupState getStartupStateById() throws DBManagerException;

    public StartupState getStartupStateByDescription(String description) throws DBManagerException;

}

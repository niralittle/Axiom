package axiom.dao;

import axiom.entity.Startup;
import axiom.dbmanager.DBManagerException;
import java.util.List;


/**
 *
 * @author Nira
 *    int id;
    String name;
    int ownerId;
    int projectType; // IT, socialStudy, small bussiness, marketing, charity
    String description;
    int startupStateID;
 */
public interface StartupDAO extends GenericDAO<Startup> {

    public Startup getStartupByName(String name)  throws DBManagerException;

    public List<Startup> getAllStartups() throws DBManagerException;

    public List<Startup> getStartupsByType(int type)  throws DBManagerException;

    public List<Startup> getActiveStartups() throws DBManagerException;

    public List<Startup> getDoneStartups() throws DBManagerException;

    boolean alreadyExist(String name) throws DBManagerException;

    boolean isActive(Startup startup) throws DBManagerException;
}

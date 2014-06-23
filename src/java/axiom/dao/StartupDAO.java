package axiom.dao;

import axiom.entity.Startup;
import axiom.dbmanager.DBManagerException;
import java.util.List;

/**
 *
 * @author Nira
 *
 */
public interface StartupDAO extends GenericDAO<Startup> {

    public List<Startup> getStartupsByName(String name, int offset,
            int numberOfRecords) throws DBManagerException; 

    public List<Startup> getAllStartups(int offset, int numberOfRecords)
            throws DBManagerException;

    public List<Startup> getStartupsByProjectType(int typeID, int offset,
            int numberOfRecords) throws DBManagerException;

    public List<Startup> getStartupsByState(int stateID, int offset,
            int numberOfRecords) throws DBManagerException;

    public int createNewStartup (Startup startup) throws  DBManagerException;
    
 }

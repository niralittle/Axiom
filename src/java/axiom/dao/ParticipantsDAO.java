package axiom.dao;

import axiom.entity.User;
import axiom.entity.Startup;
import axiom.dbmanager.DBManagerException;

import java.util.List;

/**
 *
 * @author user
 */
public interface ParticipantsDAO extends GenericDAO<ParticipantsDAO>{

    public List<User> getStartupTeam (Startup startup) throws DBManagerException;

    public List<Startup> getUserStartup (User user) throws DBManagerException;

    void addParticipantToStartup(Startup startup, User user) throws DBManagerException;

    void deleteParticipantFromStartup(Startup startup, User user) throws DBManagerException;

}

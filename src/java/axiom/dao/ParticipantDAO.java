package axiom.dao;

import axiom.entity.Participant;
import axiom.entity.User;
import axiom.dbmanager.DBManagerException;

import java.util.List;

/**
 *
 * @author user
 */
public interface ParticipantDAO extends GenericDAO<Participant>{

    public List<User> getParticipants(int startupID) throws DBManagerException;

    void addParticipantToStartup(int startupID, int userID, String role)
            throws DBManagerException;

    void deleteParticipantFromStartup(int startupID, int userID)
            throws DBManagerException;
}

package axiom.dao;

import axiom.entity.User;
import axiom.entity.Startup;
import axiom.dbmanager.DBManagerException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nira
 */
public interface UserDAO extends GenericDAO<User>{

    void blockUser(int userID) throws DBManagerException;

    boolean isLoginDuplicate(String login) throws DBManagerException;

    boolean isEmailDuplicate(String email) throws DBManagerException;

    public List<User> getUsersByName(String firstName, String secondName,
                                     int offset, int numbOfRecords)
                                     throws DBManagerException;

    public List<User> getUsersByMajor(int majorID, int offset,
            int numberOfRecords) throws DBManagerException;

    public List<User> getUsersByFaculty(int facultyID, int offset,
            int numberOfRecords) throws DBManagerException;

    public List<User> getUsersBySkill(int skillId, int offset,
            int numberOfRecords) throws DBManagerException;

    public int getUserByLogin(String login) throws  DBManagerException;

    public int registerNewUser(User user) throws  DBManagerException;
    /**
     *
     * @param userId
     * @param offset
     * @param numberOfRecords
     * @return map of startups (to retrieve name and id)
     * and strings (describing role)
     * @throws DBManagerException
     */
    public Map<Startup, String> participantOf(int userId, int offset,
            int numberOfRecords) throws DBManagerException;
}

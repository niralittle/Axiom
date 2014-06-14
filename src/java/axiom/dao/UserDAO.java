package axiom.dao;

import axiom.entity.User;
import axiom.entity.Startup;
import axiom.dbmanager.DBManagerException;
import java.util.List;

/**
 *
 * @author Nira
 */
public interface UserDAO extends GenericDAO<User>{

    /**
     * Method for block user's account i DB
     */

    void blockUser(User user) throws DBManagerException;

    boolean isValid(User user) throws DBManagerException;

    boolean alreadyExist (User user) throws DBManagerException;

    boolean isEmailDuplicate(String email) throws DBManagerException;

    public List<User> getUsersByName (String firstName, String secondName,
                                     int offset, int numbOfRecords)
                                     throws DBManagerException;
    public User getUserByName (String firstName, String secondName)
                                     throws DBManagerException;
    public List<User> getUsersByMajor (int majorId) throws DBManagerException;

    public List<User> getUsersByFaculty (int facultyId) throws DBManagerException;

    public List<User> getUsersBySkill (int skillId) throws DBManagerException;

    String status(int userId) throws DBManagerException;

    int score (int userId) throws DBManagerException;

    public List<Startup> showUserStartups (int userId) throws DBManagerException;

    public String showUserRole (Startup startup) throws DBManagerException;

}

package axiom.controllers;

import axiom.dao.UserDAO;
import axiom.dao.impl.UserDAOImpl;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.states.ProfileState;
import axiom.entity.User;

/**
 *
 * @author Nira
 */
public class UserController extends Controller {

 public int register(String login, String password, String email,
        String firstName, String lastName, int facultyID, int majorID)
        throws DBManagerException {
    UserDAO userDAO = null;
    int userID;
    try {
        if (isInternal) {
            dbManager = new DBManager();
        }
        userDAO = new UserDAOImpl(dbManager);
        // check inputted login
        if (userDAO.isLoginDuplicate(login)) {
            throw new DBManagerException("Користувач із указаним логіном " +
                    login + " вже існує, будь ласка, оберіть інший.");
        }
        // check input email
        if (userDAO.isEmailDuplicate(email)) {
            throw new DBManagerException("Вказаний емейл " + email
                    + " вже зареєстровано в системі. Оберіть інший. ");
        }
        // create new user if unique login and email
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setFacultyID(facultyID);
        user.setMajorID(majorID);
        user.setProfileState(ProfileState.READONLY.toInt()); 
        userID = (Integer) userDAO.registerNewUser(user);

        if (isInternal) {
            dbManager.commit();
        }
     } catch (DBManagerException exc) {
        if (isInternal) {
            System.out.println("Didn't write to database");
            dbManager.rollback();
        }
        throw new DBManagerException(exc.getMessage());
    } finally {
        if (isInternal) {
            dbManager.close();
        }
    }
    return userID;
    }
}

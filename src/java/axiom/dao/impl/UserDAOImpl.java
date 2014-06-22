package axiom.dao.impl;

import axiom.entity.User;
import axiom.dao.UserDAO;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.Startup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nira
 */
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    public UserDAOImpl(DBManager dbManager) {
        super(dbManager);
    }

    public void blockUser(int userID) throws DBManagerException {
        Statement statement = null;
        String query = "UPDATE SYSUSER SET PROFILESTATE = 3 WHERE id = ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, userID);
            statement.executeUpdate();
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                                "please, contact an administrator");
        } finally {
            statement.close();
        }
    }

    public boolean isLoginDuplicate(String login) throws DBManagerException {
        if (login == null || login.isEmpty()) {
             throw new DBManagerException("Passed parameter <login>" +
                        " is null or empty . Can't proccess the request!");
    	}
    	Statement statement = null;
    	ResultIterator ri = null;
    	boolean isExist = false;

        String queryString = "SELECT s.id FROM SYSUSER s WHERE s.login = ?";
        try {
            statement = dbManager.prepareStatement(queryString);
            statement.setString(1, login);

            ri = statement.executeQuery();

            // check if statement returned any value
            if (ri.next()) {
                    isExist = true;		// login exists
            } else {
                    isExist = false;		// login doesn't exist
            }
        //} catch (DBManagerException exc) {
        //    throw new DBManagerException ("The error was occured, " +
        //                    "contact the administrator");
        } finally {
            statement.close();
        }
        return isExist;
    }

    public boolean isEmailDuplicate(String email) throws DBManagerException {
        if (email == null || email.isEmpty()) {
             throw new DBManagerException("Passed parameter <login>" +
                        " is null or empty . Can't proccess the request!");
    	}
    	Statement statement = null;
    	ResultIterator ri = null;
    	boolean isExist = false;

        String queryString = "SELECT login FROM SYSUSER WHERE EMAIL = ?";
        try {
            statement = dbManager.prepareStatement(queryString);
            statement.setString(1, email);

            ri = statement.executeQuery();

            // check if statement returned any value
            if (ri.next()) {
                    isExist = true;		// exists
            } else {
                    isExist = false;		// doesn't exist
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "contact the administrator");
        } finally {
            statement.close();
        }
        return isExist;
    }

    public List<User> getUsersByName(String firstName,
            String lastName, int offset, int numberOfRecords)
            throws DBManagerException {

        if (numberOfRecords < 1 || offset < 0) {
            throw new DBManagerException("Illegal argument in paging - less than 1. "
                            + " Can't proccess the request!");
    	}

    	Statement statement = null;
    	List<User> users = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM SYSUSER s ";
        if (firstName != null) {
            query +=    "WHERE s.FIRSTNAME = ? ";
            if (lastName != null) query +=
                        "AND s.LASTNAME = ? ";
        } else if (lastName != null)  query +=
                        "WHERE s.LASTNAME = ? ";
            query +=    "ORDER BY s.LASTNAME " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            if (firstName != null) { 
                if (lastName != null) {
                    statement.setString(1, firstName);
                    statement.setString(2, lastName);
                    statement.setInt(3, offset + numberOfRecords);
                    statement.setInt(4, offset); 
                } else {
                    statement.setString(1, firstName);
                    statement.setInt(2, offset + numberOfRecords);
                    statement.setInt(3, offset); 
                }
            } else { 
                    statement.setString(1, lastName);
                    statement.setInt(2, offset + numberOfRecords);
                    statement.setInt(3, offset);
            }

            ri = statement.executeQuery();
            users = new ArrayList<User>();

            while (ri.next()) {
                User u = new User();
                u.setId(ri.getInt("id"));
                u.setFirstName(ri.getString("FIRSTNAME"));
                u.setLastName(ri.getString("LASTDNAME"));
                u.setEmail(ri.getString("EMAIL"));
                u.setBirthdate(ri.getDate("BIRTHDATE"));
                u.setMajorID(ri.getInt("MAJORID"));
                u.setFacultyID(ri.getInt("FACULTYID"));
                u.setStatus(ri.getString("STATUS"));
                u.setProfileState(ri.getInt("PROFILESTATE"));
                u.setPassword(ri.getString("PASSWORD"));
                u.setAvatarID(ri.getInt("AVATARID"));
                u.setValidSince(ri.getDate("VALIDSINCE"));
                u.setRegistrationDate(ri.getDate("REGISTRATIONDATE"));
                u.setScore(ri.getInt("SCORE"));
                List<Integer> confirmers = new ArrayList<Integer>();
                confirmers.add(ri.getInt("CONFIRMER1"));
                confirmers.add(ri.getInt("CONFIRMER2"));
                confirmers.add(ri.getInt("CONFIRMER3"));
                u.setConfirmers(confirmers);
                users.add(u);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "contact the administrator");
        } finally {
            statement.close();
        }
        return users;
    }

    public List<User> getUsersByMajor(int majorID, int offset,
            int numberOfRecords) throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
            throw new DBManagerException("Illegal argument in paging - less than 1. "
                            + " Can't proccess the request!");
    	}
    	Statement statement = null;
    	List<User> users = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM SYSUSER s " +
                        "WHERE s.MAJORID = ? " +
                        "ORDER BY s.LASTNAME " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, majorID);
            statement.setInt(2, offset + numberOfRecords);
            statement.setInt(3, offset); 

            ri = statement.executeQuery();
            users = new ArrayList<User>();

            while (ri.next()) {
                User u = new User();
                u.setId(ri.getInt("id"));
                u.setFirstName(ri.getString("FIRSTNAME"));
                u.setLastName(ri.getString("LASTNAME"));
                u.setEmail(ri.getString("EMAIL"));
                u.setBirthdate(ri.getDate("BIRTHDATE"));
                u.setMajorID(ri.getInt("MAJORID"));
                u.setFacultyID(ri.getInt("FACULTYID"));
                u.setStatus(ri.getString("STATUS"));
                u.setProfileState(ri.getInt("PROFILESTATE"));
                u.setPassword(ri.getString("PASSWORD"));
                u.setAvatarID(ri.getInt("AVATARID"));
                u.setValidSince(ri.getDate("VALIDSINCE"));
                u.setRegistrationDate(ri.getDate("REGISTRATIONDATE"));
                u.setScore(ri.getInt("SCORE"));
                List<Integer> confirmers = new ArrayList<Integer>();
                confirmers.add(ri.getInt("CONFIRMER1"));
                confirmers.add(ri.getInt("CONFIRMER2"));
                confirmers.add(ri.getInt("CONFIRMER3"));
                u.setConfirmers(confirmers);
                users.add(u);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "contact the administrator");
        } finally {
            statement.close();
        }
        return users;
    }

    public List<User> getUsersByFaculty(int facultyID, int offset,
            int numberOfRecords) throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
            throw new DBManagerException("Illegal argument in paging - less than 1. "
                            + " Can't proccess the request!");
    	}
    	Statement statement = null;
    	List<User> users = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM SYSUSER s " +
                        "WHERE s.FACULTYID = ? " +
                        "ORDER BY s.LASTNAME " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, facultyID);
            statement.setInt(2, offset + numberOfRecords);
            statement.setInt(3, offset);

            ri = statement.executeQuery();
            users = new ArrayList<User>();

            while (ri.next()) {
                User u = new User();
                u.setId(ri.getInt("id"));
                u.setFirstName(ri.getString("FIRSTNAME"));
                u.setLastName(ri.getString("LASTNAME"));
                u.setEmail(ri.getString("EMAIL"));
                u.setBirthdate(ri.getDate("BIRTHDATE"));
                u.setMajorID(ri.getInt("MAJORID"));
                u.setFacultyID(ri.getInt("FACULTYID"));
                u.setStatus(ri.getString("STATUS"));
                u.setProfileState(ri.getInt("PROFILESTATE"));
                u.setPassword(ri.getString("PASSWORD"));
                u.setAvatarID(ri.getInt("AVATARID"));
                u.setValidSince(ri.getDate("VALIDSINCE"));
                u.setRegistrationDate(ri.getDate("REGISTRATIONDATE"));
                u.setScore(ri.getInt("SCORE"));
                List<Integer> confirmers = new ArrayList<Integer>();
                confirmers.add(ri.getInt("CONFIRMER1"));
                confirmers.add(ri.getInt("CONFIRMER2"));
                confirmers.add(ri.getInt("CONFIRMER3"));
                u.setConfirmers(confirmers);
                users.add(u);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "contact the administrator");
        } finally {
            statement.close();
        }
        return users;
        }

    public List<User> getUsersBySkill(int skillID, int offset,
            int numberOfRecords) throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
            throw new DBManagerException("Illegal argument in paging - less than 1. "
                            + " Can't proccess the request!");
    	}
    	Statement statement = null;
    	List<User> users = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM (" +
                        "SELECT * " +
                        "FROM SYSUSER s " +
                        "RIGHT JOIN ( SELECT * " +
                        "       FROM USERSKILL  " +
                        "       WHERE SKILLID = ? ) us " +
                        "ON s.ID = us.USERID " +
                        "ORDER BY s.SCORE " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, skillID);
            statement.setInt(2, offset + numberOfRecords);
            statement.setInt(3, offset);

            ri = statement.executeQuery();
            users = new ArrayList<User>();

            while (ri.next()) {
                User u = new User();
                u.setId(ri.getInt("id"));
                u.setFirstName(ri.getString("FIRSTNAME"));
                u.setLastName(ri.getString("LASTNAME"));
                u.setEmail(ri.getString("EMAIL"));
                u.setBirthdate(ri.getDate("BIRTHDATE"));
                u.setMajorID(ri.getInt("MAJORID"));
                u.setFacultyID(ri.getInt("FACULTYID"));
                u.setStatus(ri.getString("STATUS"));
                u.setProfileState(ri.getInt("PROFILESTATE"));
                u.setPassword(ri.getString("PASSWORD"));
                u.setAvatarID(ri.getInt("AVATARID"));
                u.setValidSince(ri.getDate("VALIDSINCE"));
                u.setRegistrationDate(ri.getDate("REGISTRATIONDATE"));
                u.setScore(ri.getInt("SCORE"));
                List<Integer> confirmers = new ArrayList<Integer>();
                confirmers.add(ri.getInt("CONFIRMER1"));
                confirmers.add(ri.getInt("CONFIRMER2"));
                confirmers.add(ri.getInt("CONFIRMER3"));
                u.setConfirmers(confirmers);
                users.add(u);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "contact the administrator");
        } finally {
            statement.close();
        }
        return users;
    }

    public Map<Startup, String> participantOf(int userID, int offset,
            int numberOfRecords) throws DBManagerException {

    	Statement statement = null;
    	Map<Startup, String> m;
    	ResultIterator ri  = null;
    	String query  = "SELECT s.*, p.ROLE " +
                        "FROM STARTUP s, PARTICIPANT p " +
                        "WHERE s.ID = p.STARTUPID " +
                        "AND p.USERID = ? ";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, userID);
            ri = statement.executeQuery();
            m = new HashMap<Startup, String>();
            Startup s;
            String role;
            while (ri.next()) {
                s = new Startup();
                s.setId(ri.getInt("ID"));
                s.setName(ri.getString("NAME"));
                s.setOwnerId(ri.getInt("OWNERID"));
                s.setProjectType(ri.getInt("PROJECTTYPE"));
                s.setDescription(ri.getString("DESCRIPTION"));
                s.setStartupStateID(ri.getInt("STARTUPSTATEID"));
                role = ri.getString("ROLE");
                m.put(s, role);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return m;
    }

    public int registerNewUser(User user) throws DBManagerException {
        Statement statement = null;
    	String query  = "INSERT INTO SYSUSER " +
                        "(ID, FIRSTNAME, LASTNAME, " +
                        "MAJORID, PROFILESTATE, PASSWORD, " +
                        "REGISTRATIONDATE, FACULTYID, EMAIL, LOGIN ) " +
                        "VALUES " +
                        "(SYSUSER_ID.nexval, " +
                        "?, ?, ?, " +
                        "?, ?, SYSDATE, " +
                        "?, ?, ? )";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setString(2, user.getLastName());
            statement.setString(1, user.getFirstName());
            statement.setInt(3, user.getMajorID());
            statement.setInt(4, user.getProfileState());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getFacultyID());
            statement.setString(7, user.getEmail());
            statement.setString(8, user.getLogin());
            System.out.println(statement.toString());
            statement.executeUpdate();
//         } catch (DBManagerException exc) {
//            throw new DBManagerException ("An error occured, " +
//                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return (Integer) statement.getGeneratedPrimaryKey();
    }

}

package axiom.controllers;

/**
 *
 * @author Nira
 */
import java.io.IOException;
import axiom.dao.UserDAO;
import axiom.dao.StartupDAO;
import axiom.entity.Startup;
import axiom.dao.impl.UserDAOImpl;
import axiom.dao.impl.StartupDAOImpl;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.entity.User;
import java.util.List;
import java.util.ArrayList;

public class Search extends Controller {

    static int numbOfRecords = 10;
    static int offset = 0;

    public List<User> seekUserByName(String name) throws DBManagerException {
        List<User> users = new ArrayList<User>();
        UserDAO userDAO = null;
        try {
            if (isInternal) {
                dbManager = new DBManager();
            }
            userDAO = new UserDAOImpl(dbManager);
            String firstName = name;
            String lastName = null;
            if (name.indexOf(" ")>0)
            {
                firstName.copyValueOf(name.toCharArray(), 0, name.indexOf(" "));
                lastName.copyValueOf(name.toCharArray(), name.indexOf(" ")+1, name.length()-name.indexOf(" "));
            }

            users = userDAO.getUsersByName(firstName, lastName, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            if (isInternal) {
                dbManager.rollback();
            }
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            if (isInternal) {
                dbManager.close();
            }
        }
        return users;
    }

   public List<User> seekUserByFaculty(int facultyId) throws DBManagerException {
        List<User> users = new ArrayList<User>();
        UserDAO userDAO = null;
        try {
            if (isInternal) {
                dbManager = new DBManager();
            }
            userDAO = new UserDAOImpl(dbManager);
            users = userDAO.getUsersByFaculty(facultyId, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            if (isInternal) {
                dbManager.rollback();
            }
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            if (isInternal) {
                dbManager.close();
            }
        }
        return users;
    }

   public List<User> seekUserByMajor(int majorId) throws DBManagerException {
        List<User> users = new ArrayList<User>();
        UserDAO userDAO = null;
        try {
            if (isInternal) {
                dbManager = new DBManager();
            }
            userDAO = new UserDAOImpl(dbManager);
            users = userDAO.getUsersByFaculty(majorId, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            if (isInternal) {
                dbManager.rollback();
            }
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            if (isInternal) {
                dbManager.close();
            }
        }
        return users;
    }


   public List<Startup> seekStartupByName(String name) throws DBManagerException {
        List<Startup> starts = new ArrayList<Startup>();
        StartupDAO StartupDAO = null;
        try {
            if (isInternal) {
                dbManager = new DBManager();
            }
            StartupDAO = new StartupDAOImpl(dbManager);
            starts = StartupDAO.getStartupsByName(name, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            if (isInternal) {
                dbManager.rollback();
            }
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            if (isInternal) {
                dbManager.close();
            }
        }
        return starts;
    }


   public List<Startup> seekStartupByState(int startupStateId) throws DBManagerException {
        List<Startup> starts = new ArrayList<Startup>();
        StartupDAO StartupDAO = null;
        try {
            if (isInternal) {
                dbManager = new DBManager();
            }
            StartupDAO = new StartupDAOImpl(dbManager);
            starts = StartupDAO.getStartupsByState(startupStateId, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            if (isInternal) {
                dbManager.rollback();
            }
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            if (isInternal) {
                dbManager.close();
            }
        }
        return starts;
    }


   public List<Startup> seekStartupByType(int projectTypeId) throws DBManagerException {
        List<Startup> starts = new ArrayList<Startup>();
        StartupDAO StartupDAO = null;
        try {
            if (isInternal) {
                dbManager = new DBManager();
            }
            StartupDAO = new StartupDAOImpl(dbManager);
            starts = StartupDAO.getStartupsByProjectType(projectTypeId, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            if (isInternal) {
                dbManager.rollback();
            }
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            if (isInternal) {
                dbManager.close();
            }
        }
        return starts;
    }

}

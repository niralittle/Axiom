package axiom.controllers;

/**
 *
 * @author Nira
 */
import java.io.IOException;

import axiom.dao.UserDAO;
import axiom.dao.StartupDAO;
import axiom.dao.VacancyDAO;
import axiom.dao.impl.UserDAOImpl;
import axiom.dao.impl.StartupDAOImpl;
import axiom.dao.impl.VacancyDAOImpl;

import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;

import axiom.entity.User;
import axiom.entity.Startup;
import axiom.entity.Vacancy;

import java.util.List;
import java.util.ArrayList;


public class Search extends Controller {

    static private final int numbOfRecords = 10;
    static int offset = 0;
    private DBManager dbManager = null;

    public void searchContent(int page) throws IOException {
        offset = page*numbOfRecords;

     }

   public List<User> seekUserByName(String firstName, String lastName)
           throws DBManagerException {
        List<User> users = new ArrayList<User>();
        UserDAO userDAO = null;

        try {
            userDAO = new UserDAOImpl(dbManager);
            users = userDAO.getUsersByName(firstName, lastName, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            dbManager.rollback();
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            dbManager.close();
        }
        return users;
    }

   public List<User> seekUserByFaculty(int facultyId) throws DBManagerException {
        List<User> users = new ArrayList<User>();
        UserDAO userDAO = null;
        try {
            dbManager = new DBManager();
            userDAO = new UserDAOImpl(dbManager);
            users = userDAO.getUsersByFaculty(facultyId, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            dbManager.rollback();
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            dbManager.close();
        }
        return users;
    }

   public List<User> seekUserByMajor(int majorId) throws DBManagerException {
        List<User> users = new ArrayList<User>();
        UserDAO userDAO = null;
        try {
            dbManager = new DBManager();
            userDAO = new UserDAOImpl(dbManager);
            users = userDAO.getUsersByFaculty(majorId, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            dbManager.rollback();
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            dbManager.close();
        }
        return users;
    }


   public List<Startup> seekStartupByName(String name) throws DBManagerException {
        List<Startup> starts = new ArrayList<Startup>();
        StartupDAO StartupDAO = null;
        try {
            dbManager = new DBManager();
            StartupDAO = new StartupDAOImpl(dbManager);
            starts = StartupDAO.getStartupsByName(name, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            dbManager.rollback();
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            dbManager.close();
        }
        return starts;
    }

   public List<Startup> seekStartupByType(int projectTypeId) throws DBManagerException {
        List<Startup> starts = new ArrayList<Startup>();
        StartupDAO StartupDAO = null;
        try {
            dbManager = new DBManager();
            StartupDAO = new StartupDAOImpl(dbManager);
            starts = StartupDAO.getStartupsByProjectType(projectTypeId, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            dbManager.rollback();
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            dbManager.close();
        }
        return starts;
    }


   public List<Startup> seekStartupByState(int startupStateId) throws DBManagerException {
        List<Startup> starts = new ArrayList<Startup>();
        StartupDAO StartupDAO = null;
        try {
            dbManager = new DBManager();
            StartupDAO = new StartupDAOImpl(dbManager);
            starts = StartupDAO.getStartupsByState(startupStateId, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            dbManager.rollback();
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            dbManager.close();
        }
        return starts;
    }
   
   public List<Vacancy> seekVacancyBySkill(int skillId) throws DBManagerException{
       List <Vacancy> vacancies = new ArrayList<Vacancy>();
       VacancyDAO vacDAO = null;
       try {
            dbManager = new DBManager();
            vacDAO = new VacancyDAOImpl(dbManager);
            vacancies = vacDAO.getVacanciesBySkill(skillId, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            dbManager.rollback();
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            dbManager.close();
        }
       return vacancies;
   
   }

   public List<Vacancy> seekVacancyByStartup(int startupId) throws DBManagerException{
       List <Vacancy> vacancies = new ArrayList<Vacancy>();
       VacancyDAO vacDAO = null;
       try {
            dbManager = new DBManager();
            vacDAO = new VacancyDAOImpl(dbManager);
            vacancies = vacDAO.getVacancyByStartup(startupId, offset, numbOfRecords);
        }
        catch(DBManagerException exc) {
            dbManager.rollback();
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            dbManager.close();
        }
        return vacancies;
   }

   public List<Vacancy> seekVacancyByName(String name) throws DBManagerException{
       List <Vacancy> vacancies = new ArrayList<Vacancy>();
       VacancyDAO vacDAO = null;
       try {
            dbManager = new DBManager();
            vacDAO = new VacancyDAOImpl(dbManager);
            vacancies.add(vacDAO.getVacancyByName(name));
        }
        catch(DBManagerException exc) {
            dbManager.rollback();
            throw new DBManagerException(exc.getMessage());
        }
        finally {
            dbManager.close();
        }
       return vacancies;

   }

}

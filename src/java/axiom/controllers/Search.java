package axiom.controllers;

/**
 *
 * @author Nira
 */
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import axiom.dao.UserDAO;
import axiom.dao.StartupDAO;
import axiom.dao.impl.UserDAOImpl;
import axiom.dao.impl.StartupDAOImpl;
import axiom.dao.FacultyDAO;
import axiom.dao.MajorDAO;
import axiom.dao.impl.FacultyDAOImpl;
import axiom.dao.impl.MajorDAOImpl;
import axiom.dao.StartupStateDAO;
import axiom.dao.ProjectTypeDAO;
import axiom.dao.impl.StartupStateDAOImpl;
import axiom.dao.impl.ProjectTypeDAOImpl;
import axiom.dao.SkillDAO;
import axiom.dao.impl.SkillDAOImpl;

import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;

import axiom.entity.User;
import axiom.entity.Startup;
import axiom.entity.Faculty;
import axiom.entity.Major;
import axiom.entity.Skill;
import axiom.entity.StartupState;
import axiom.entity.ProjectType;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class Search extends HttpServlet {

    static private final int numbOfRecords = 10;
    static int offset = 0;
    private DBManager dbManager = null;

     @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        Map <Integer, Startup> startups = null;
        Map <Integer, User> users = null;

        int page = 1;
        String pageAttr = request.getParameter("page");
        try {
            page = (pageAttr == null) ? 1 : Integer.parseInt(pageAttr);
        } catch (NumberFormatException ex) {
            page = 1;
        }

        try{
            int i = 0;
            FacultyDAO fDAO = new FacultyDAOImpl(dbManager);
            Map <Integer,Faculty> faculties = new HashMap <Integer,Faculty> ();
            List<Faculty> fac = fDAO.getAllFaculties(0,10);
            for (Faculty f: fac) faculties.put(i++, f);

            i = 0;
            MajorDAO mDAO = new MajorDAOImpl(dbManager);
            Map <Integer,Major> majors = new HashMap <Integer,Major> ();
            List<Major> maj = mDAO.getAllMajors(0,10);
            for (Major m: maj) majors.put(i++, m);

            i = 0;
            ProjectTypeDAO ptDAO = new ProjectTypeDAOImpl(dbManager);
            Map <Integer,ProjectType> projectTypes = new HashMap <Integer,ProjectType> ();
            List<ProjectType> pt = ptDAO.getAllProjectTypes();
            for (ProjectType a: pt) projectTypes.put(i++, a);

            i = 0;
            StartupStateDAO ssDAO = new StartupStateDAOImpl(dbManager);
            Map <Integer,StartupState> startupstates = new HashMap <Integer,StartupState> ();
            List<StartupState> ss = ssDAO.getAllStartupStates();
            for (StartupState a: ss) startupstates.put(i++, a);

            i = 0;
            SkillDAO sDAO = new SkillDAOImpl(dbManager);
            Map <Integer,Skill> skills = new HashMap <Integer,Skill> ();
            List<Skill> s = sDAO.getAllSkills(0, 9);
            for (Skill a: s) skills.put(i++, a);


            if ("user".equals(request.getAttribute("kind"))){
                users = new HashMap <Integer, User>();
                String name = (String)request.getAttribute("name");
                String facultyAttr = (String) request.getAttribute("faculty");
                int facultyId = (facultyAttr == null)? 0 : Integer.parseInt(facultyAttr);
                String majorAttr = (String) request.getAttribute("major");
                int majorId = (majorAttr == null)? 0 : Integer.parseInt(majorAttr);
                if (name != null) 
                    users = userListToMap(seekUserByName(name));
                else{
                    if (majorId>0)
                       users = userListToMap(seekUserByMajor(majorId));
                    else
                       if (facultyId>0)
                          users = userListToMap(seekUserByFaculty(facultyId));
                    }
                 request.setAttribute("users", users);

            }
            else{
                startups = new HashMap <Integer, Startup>();
                String name = (String)request.getAttribute("name");
                String typeAttr = (String) request.getAttribute("startupType");
                int typeId = (typeAttr == null)? 0 : Integer.parseInt(typeAttr);
                String stateAttr = (String) request.getAttribute("startupState");
                int stateId = (stateAttr == null)? 0 : Integer.parseInt(stateAttr);
                if (name != null)
                    startups = startupListToMap(seekStartupByName(name));
                else {
                    if (stateId>0)
                        startups = startupListToMap(seekStartupByState(stateId));
                    else
                        if (typeId>0)
                                startups = startupListToMap(seekStartupByType(typeId));
                    }
                request.setAttribute("startups", startups);
            }

        }catch(DBManagerException dbmEx)
        {
           // dbManager.rollback();
        }
        finally
        {
            dbManager.close();
        }


     }

   private HashMap<Integer, User> userListToMap (List<User> users){
       HashMap<Integer, User> usersMap = new HashMap<Integer, User>();
       int i = 0;
       for(User u:users){
           usersMap.put(i, u);
           ++i;
       }
       return usersMap;
   }

   private HashMap<Integer, Startup> startupListToMap (List<Startup> starts){
       HashMap<Integer, Startup> startsMap = new HashMap<Integer, Startup>();
       int i = 0;
       for(Startup s:starts){
           startsMap.put(i, s);
           ++i;
       }
       return startsMap;
   }

   public List<User> seekUserByName(String name) throws DBManagerException {
        List<User> users = new ArrayList<User>();
        UserDAO userDAO = null;
        try {
            userDAO = new UserDAOImpl(dbManager);
            String firstName = name;
            String lastName = null;
            if (name.indexOf(" ")>0)
            {
                firstName.copyValueOf(name.toCharArray(), 0, name.indexOf(" "));
                lastName.copyValueOf(name.toCharArray(), name.indexOf(" ")+1, name.length()-name.indexOf(" "));
            }

            users = userDAO.getUsersByName(firstName, lastName, offset, numbOfRecords);
            if (users.isEmpty())
                users = userDAO.getUsersByName(lastName, firstName, offset, numbOfRecords);
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

}

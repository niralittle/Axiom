package axiom.servlets;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import axiom.entity.Vacancy;
import axiom.entity.Faculty;
import axiom.entity.Major;
import axiom.entity.Skill;
import axiom.entity.StartupState;
import axiom.entity.ProjectType;

import axiom.controllers.Search;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Nira
 */
public class SearchVacancy extends HttpServlet {


    int page = 1;
    Search searchContent;
    private DBManager dbManager = null;
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */



    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
         try{
            dbManager = new DBManager();

            FacultyDAO fDAO = new FacultyDAOImpl(dbManager);
            List<Faculty> faculties = fDAO.getAllFaculties(0,10);

            MajorDAO mDAO = new MajorDAOImpl(dbManager);
            List<Major> majors = mDAO.getAllMajors(0,10);

            ProjectTypeDAO ptDAO = new ProjectTypeDAOImpl(dbManager);
            List<ProjectType> projectTypes = ptDAO.getAllProjectTypes();

            StartupStateDAO ssDAO = new StartupStateDAOImpl(dbManager);
            List<StartupState> startupstates = ssDAO.getAllStartupStates();

            SkillDAO sDAO = new SkillDAOImpl(dbManager);
            List<Skill> skills = sDAO.getAllSkills(0, 9);

            request.setAttribute("faculties", faculties);
            request.setAttribute("majors", majors);
            request.setAttribute("projectTypes", projectTypes);
            request.setAttribute("startupstates", startupstates);
            request.setAttribute("skills", skills);
        }catch(DBManagerException dbmEx)
        {
            Logger.getLogger(SearchStartup.class.getName()).log(Level.SEVERE, null, dbmEx);
        }
        finally
        {
            dbManager.close();
            doPost(request,response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
        RequestDispatcher view = request.getRequestDispatcher("search.jsp");
        view.forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (!("user".equals(request.getAttribute("kind"))||
              "startup".equals(request.getAttribute("kind")))) try {
            processRequest(request, response);
            Search searchContent = new Search();
            searchContent.searchContent(page);
            ArrayList<Vacancy> vacancies = new ArrayList <Vacancy>();
            String name = (String)request.getAttribute("name");
            String skillAttr = (String) request.getAttribute("skill");
            int skillId = (skillAttr == null)? 0 : Integer.parseInt(skillAttr);
            String startupAttr = (String) request.getAttribute("startup");
            int startupId = (startupAttr == null)? 0 : Integer.parseInt(startupAttr);
            if (name != null)
                vacancies = (ArrayList<Vacancy>)searchContent.seekVacancyByName(name);
            else{
                if (skillId>0)
                   vacancies = (ArrayList<Vacancy>)searchContent.seekVacancyBySkill(skillId);
                else
                   if (startupId>0)
                      vacancies = (ArrayList<Vacancy>)searchContent.seekVacancyByStartup(startupId);
                }
             request.setAttribute("vacancies", vacancies);

         }catch (DBManagerException ex) {
             Logger.getLogger(SearchStartup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet seeks users in DataBase with searchController's help";
    }// </editor-fold>

}

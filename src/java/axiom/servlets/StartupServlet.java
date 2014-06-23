package axiom.servlets;

import axiom.controllers.NewStartupController;

import axiom.dao.ProjectTypeDAO;
import axiom.dao.StartupStateDAO;
import axiom.dao.StartupDAO;
import axiom.dao.UserDAO;
import axiom.dao.VacancyDAO;
import axiom.dao.impl.ProjectTypeDAOImpl;
import axiom.dao.impl.StartupStateDAOImpl;
import axiom.dao.impl.StartupDAOImpl;
import axiom.dao.impl.UserDAOImpl;
import axiom.dao.impl.VacancyDAOImpl;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;

import axiom.entity.User;
import axiom.entity.Vacancy;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StartupServlet extends HttpServlet{
    
    private int startupId;

     protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) {

    }



    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            prepareDataForRegistrationPage(request);
        } catch (DBManagerException ex) {
            java.util.logging.Logger.getLogger(RegistrationServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        RequestDispatcher view = request.getRequestDispatcher("Startup"+startupId+".jsp");
        view.forward(request, response);
     }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) {

    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Show startup";
    }// </editor-fold>


    private void prepareDataForRegistrationPage(HttpServletRequest req)
             throws IOException, DBManagerException {
        DBManager dbManager = null;
        try {
            dbManager = new DBManager();
            StartupDAO startupDAO = new StartupDAOImpl(dbManager);
            StartupStateDAO ssDAO = new StartupStateDAOImpl(dbManager);
            ProjectTypeDAO ptDAO = new ProjectTypeDAOImpl(dbManager);
            UserDAO userDAO = new UserDAOImpl(dbManager);
            VacancyDAO vacDAO = new VacancyDAOImpl(dbManager);
            ArrayList<Vacancy> vacancies = new ArrayList<Vacancy>();
            ArrayList<User> team = new ArrayList<User>();
            int ID = req.getAttribute("startupId");
            req.setAttribute("projectTypes", prTypes);
            req.setAttribute("startupStates", startupStates);
            req.setAttribute("name", name);
            req.setAttribute("description", description);
            req.setAttribute("vacancies", vacancies);
        } catch (DBManagerException ex) {
            Logger.getLogger(RegistrationServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            dbManager.close();
        }
    }

}

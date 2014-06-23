package axiom.servlets;

import axiom.dao.ParticipantDAO;
import axiom.dao.StartupDAO;
import axiom.dao.VacancyDAO;
import axiom.dao.impl.ParticipantDAOImpl;
import axiom.dao.impl.StartupDAOImpl;
import axiom.dao.impl.VacancyDAOImpl;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;

import axiom.entity.Startup;
import axiom.entity.User;
import axiom.entity.Vacancy;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class StartupServlet extends HttpServlet{
    
    private int ID;

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
        RequestDispatcher view = request.getRequestDispatcher("Startup"+ID+".jsp");
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
            ID = (Integer)req.getAttribute("startupId");
            StartupDAO startupDAO = new StartupDAOImpl(dbManager);
            ParticipantDAO partDAO = new ParticipantDAOImpl(dbManager);
            VacancyDAO vacDAO = new VacancyDAOImpl(dbManager);
            List<Vacancy> vacancies = new ArrayList<Vacancy>();
            vacancies = vacDAO.getVacancyByStartup(ID, 0, 20);
            List<User> team = new ArrayList<User>();
            team = partDAO.getParticipants(ID);
            Startup start = startupDAO.getStartupById(ID);
            req.setAttribute("projectTypes", start.getProjectType());
            req.setAttribute("startupStates", start.getStartupStateID());
            req.setAttribute("name", start.getName());
            req.setAttribute("description", team);
            req.setAttribute("vacancies", vacancies);
        } catch (DBManagerException ex) {
            Logger.getLogger(RegistrationServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            dbManager.close();
        }
    }

}

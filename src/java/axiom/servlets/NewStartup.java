package axiom.servlets;

/**
 *
 * @author user
 */
import axiom.controllers.NewStartupController;

import axiom.dao.ProjectTypeDAO;
import axiom.dao.StartupStateDAO;
import axiom.dao.UserDAO;
import axiom.dao.impl.ProjectTypeDAOImpl;
import axiom.dao.impl.StartupStateDAOImpl;
import axiom.dao.impl.UserDAOImpl;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
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


public class NewStartup extends HttpServlet {

    private static final String NAME_PATTERN = "^[A-Za-zА-Яа-я0-9_-]{3,40}$";
    private static final String DESCRIPTION_PATTERN = "^[A-Za-zА-Яа-я0-9!@#$%^&*()_]{6,40}$";

    private static final String CREATE_STARTUP_PAGE = "registration.jsp";
    private static final String VACANCY_PAGE = "newStartupVacancies.jsp";
    //UserController credentials
    private String name;
    private String description;;
    private int ownerId;
    private int projectTypeId;
    private int startupStateId;
    //captcha
    private String inputtedCaptcha;
    private String generatedCaptcha;


        protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, DBManagerException {
        //local variable derclaration
        boolean isParamsValid;
        StringBuilder errMessage = new StringBuilder();
        if("Refresh".equals(request.getParameter("action"))) {
            redirectTo(CREATE_STARTUP_PAGE, request, response);
        } else {
        // logic actions
            readParameters(request);
            isParamsValid = validateParams(errMessage);

            if (!isParamsValid) {
                request.setAttribute("errMessage", errMessage);
                redirectTo(CREATE_STARTUP_PAGE, request, response);
            } else {
                try {
                    NewStartupController startControl = new NewStartupController();
                    int check = startControl.createNewStartup(name, description, ownerId, projectTypeId, startupStateId);
                    System.out.println("Success! StartupID = " + check);
                    request.setAttribute("startupId", check);
                    redirectTo(VACANCY_PAGE, request, response);
                //} catch (Exception e) {
                //    System.out.println("Something went wrong when trying " +
                //            "to create a new startup");
                } finally {

                }
            }
        }
    }

    /**
     * Read inputted params from request scope.
     * @param request
     */
    private void readParameters(HttpServletRequest request)
            throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        name = request.getParameter("name");
        description = request.getParameter("description");
        try{
            DBManager dbManager = new DBManager();
            UserDAO userDAO = new UserDAOImpl(dbManager);
            ownerId = userDAO.getUserByLogin(request.getRemoteUser());
        }catch(DBManagerException exc){
            Logger.getLogger(NewStartupController.class.getName()).log(Level.SEVERE, null, exc);
        }
        projectTypeId = Integer.parseInt(request.getParameter("projectType"));
        startupStateId = Integer.parseInt(request.getParameter("startupState"));
// read captcha
        inputtedCaptcha = request.getParameter("code");
        generatedCaptcha = (String) request.getSession().getAttribute("captcha");

    }

    /**
     * Redirect to passes page.
     *
     * @param request
     * @param response
     * @param page
     * @throws ServletException
     * @throws IOException
     */
    private void redirectTo(String page, HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher view = request.getRequestDispatcher(page);
        view.forward(request, response);
        return;
    }

    private boolean validateParams(StringBuilder errMessage) {

        Pattern pattern;
        Matcher matcher;

        boolean isValid = true;

        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            isValid = false;
            errMessage.append("- Введіть назву.<br />");
        }

        pattern = Pattern.compile(DESCRIPTION_PATTERN);
        matcher = pattern.matcher(description);
        if (!matcher.matches()) {
            isValid = false;
            errMessage.append("- Введіть опис.<br />");
        }
        return isValid;
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
        RequestDispatcher view = request.getRequestDispatcher("newStartup.jsp");
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
            HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DBManagerException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            RuntimeException e = new RuntimeException();
            e.initCause(ex.getCause());
            throw e;
        }

    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Create new startup";
    }// </editor-fold>


    private void prepareDataForRegistrationPage(HttpServletRequest req)
             throws IOException, DBManagerException {
        DBManager dbManager = null;
        try {
            dbManager = new DBManager();
            ProjectTypeDAO ptDAO = new ProjectTypeDAOImpl(dbManager);
            StartupStateDAO ssDAO = new StartupStateDAOImpl(dbManager);
            List prTypes = ptDAO.getAllProjectTypes();
            List startupStates = ssDAO.getAllStartupStates();
            req.setAttribute("projectTypes", prTypes);
            req.setAttribute("startupStates", startupStates);
        } catch (DBManagerException ex) {
            Logger.getLogger(RegistrationServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            dbManager.close();
        }
    }


}

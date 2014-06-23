package axiom.servlets;

import axiom.controllers.UserController;

import axiom.dao.FacultyDAO;
import axiom.dao.MajorDAO;
import axiom.dao.impl.FacultyDAOImpl;
import axiom.dao.impl.MajorDAOImpl;
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

/**
 * Provides registration in system for new user and creates new scenario
 * workflow for order with status "Entering"
 *
 */
public class RegistrationServlet extends HttpServlet {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String LOGIN_PATTERN = "^[A-Za-z0-9_-]{3,40}$";
   // private static final String NAME_PATTERN = "^[А-Яа-я]{3,40}$";
    private static final String PASSWORD_PATTERN = "^[A-Za-z0-9!@#$%^&*()_]{6,40}$";
    private static Logger logger = Logger.getLogger(RegistrationServlet.class.getName());
    // pages to redirect
    private static final String REGISTRATION_PAGE = "registration.jsp";
    private static final String CONGRATULATION_PAGE = "index.jsp";
    //UserController credentials
    private String login;
    private String password;
    private String passwordConf;
    private String email;
    private String firstName;
    private String lastName;
    private int faculty;
    private int major;
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
            redirectTo(REGISTRATION_PAGE, request, response);
        } else {
        // logic actions
            readParameters(request);
            isParamsValid = validateParams(errMessage);

            if (!isParamsValid) {
                request.setAttribute("errMessage", errMessage);
                redirectTo(REGISTRATION_PAGE, request, response);
            } else {
                try {
                    UserController userControl = new UserController();
                    int check = userControl.register(login, password, email, firstName,
                                    lastName,faculty, major);
                    System.out.println("Success! UserID = " + check);
                    redirectTo(CONGRATULATION_PAGE, request, response);
                } catch (Exception e) {
                    System.out.println("Something went wrong when trying " +
                            "to create a new user");
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
        login = request.getParameter("login");
        password = request.getParameter("password");
        passwordConf = request.getParameter("passwordConf");
        email = request.getParameter("email");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        System.out.println("Faculty: " + request.getParameter("faculty"));
        System.out.println("Major: " + request.getParameter("major"));
        faculty = Integer.parseInt(request.getParameter("faculty"));
        major = Integer.parseInt(request.getParameter("major"));
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

        pattern = Pattern.compile(LOGIN_PATTERN);
        matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            isValid = false;
            errMessage.append("- Логін має бути " +
         "не коротший від трьох символів, без пробілів та не більше 30 символів у довжину.<br />");
        }

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            isValid = false;
            errMessage.append("- Будь ласка, введіть валідну емейл-адресу. <br />");
        }
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            isValid = false;
            errMessage.append("- Пароль не коротший від шести символів.<br />");
        }
        if (!password.equals(passwordConf)) {
            isValid = false;
            errMessage.append("- Паролі не співпадають!<br />");
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
        RequestDispatcher view = request.getRequestDispatcher("registration.jsp");
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
        return "Registers user in the system, creates a new order " +
                "and executes it ('New' scenario workflow).";
    }// </editor-fold>


    private void prepareDataForRegistrationPage(HttpServletRequest req)
             throws IOException, DBManagerException {
         DBManager dbManager = null;
         int offset = 0;
         int numbOfRec = 100;
        try {
            dbManager = new DBManager();
            FacultyDAO facDAO = new FacultyDAOImpl(dbManager);
            MajorDAO majDAO = new MajorDAOImpl(dbManager);
            List facs = facDAO.getAllFaculties(offset, numbOfRec);
            List majors = majDAO.getAllMajors(offset, numbOfRec);
            System.out.println("Retrieved data by DAO: ");
            System.out.println("is empty: " + majors.isEmpty());
            req.setAttribute("faculties", facs);
            req.setAttribute("majors", majors);
        } catch (DBManagerException ex) {
            Logger.getLogger(RegistrationServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            dbManager.close();
        }
    }
}

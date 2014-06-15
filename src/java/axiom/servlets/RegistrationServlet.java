package axiom.servlets;

import axiom.controllers.UserController;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
    private static final String PASSWORD_PATTERN = "^[A-Za-z0-9!@#$%^&*()_]{6,40}$";
    private static Logger logger = Logger.getLogger(RegistrationServlet.class.getName());
    // pages to redirect
    private static final String REGISTRATION_PAGE = "registration.jsp";
    private static final String CONGRATULATION_PAGE = "success.jsp";
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
            throws ServletException, IOException {
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
                    UserController userControl = null;
                    userControl = new UserController();
                    userControl.register(login, password, email, firstName,
                                    lastName,faculty, major);
                    redirectTo(CONGRATULATION_PAGE, request, response);
                } catch (Exception e) {

                } finally {

                }
            }
        }
    }



    /**
     * Read inputted params from request scope.
     * @param request
     */
    private void readParameters(HttpServletRequest request) {
        login = request.getParameter("login");
        password = request.getParameter("password");
        passwordConf = request.getParameter("passwordConf");
        email = request.getParameter("email");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
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
        matcher = pattern.matcher(lastName);
        if (!matcher.matches()) {
            isValid = false;
            errMessage.append("- Прізвище повинно бути без пробілів.<br />");

        }
        matcher = pattern.matcher(firstName);
        if (!matcher.matches()) {
            isValid = false;
            errMessage.append("- Введіть ім'я без пробілів.<br />");
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
        processRequest(request, response);
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
        processRequest(request, response);

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
}

package axiom.servlets;

import axiom.controllers.VacancyController;

import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;

import java.util.HashMap;

import java.io.IOException;
import java.sql.Date;
import java.io.UnsupportedEncodingException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class NewStartupVacancy extends HttpServlet {

    private static final String NAME_PATTERN = "^[A-Za-zА-Яа-я0-9_-]{3,40}$";
    private static final String DESCRIPTION_PATTERN = "^[A-Za-zА-Яа-я0-9!@#$%^&*()_]{6,40}$";

    private static final String CREATE_VACANCIES_PAGE = "newStartupVacancy.jsp";
    private static final String CONGRATULATION_PAGE = "index.jsp";

    private HashMap <String,String> vacancies;
    private int startupId;
    private Date date;

        protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, DBManagerException {
  
        if("Refresh".equals(request.getParameter("action"))) {
            redirectTo(CREATE_VACANCIES_PAGE, request, response);
        } else {
     
            readParameters(request);{
            try {
                VacancyController vacancyControl = new VacancyController();
                for (String v: vacancies.keySet())
                    vacancyControl.createNewVacncy(v, vacancies.get(v), startupId, date);
                redirectTo(CONGRATULATION_PAGE, request, response);
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
        startupId = (Integer)request.getSession().getAttribute("startupId");
        int numb = (Integer)request.getAttribute("numbOfVacancies");
        String name;
        String description;
        for (int i = 0; i < numb; i++){
            name = request.getParameter("name"+Integer.toString(i));
            description = request.getParameter("description"+Integer.toString(i));
            vacancies.put(name, description);
        }
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

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("newStartupVacancy.jsp");
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
        return "Create vacancies fornew startup";
    }// </editor-fold>


}

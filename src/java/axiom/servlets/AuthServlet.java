package axiom.servlets;

import java.io.IOException;

import axiom.dbmanager.DBManagerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class AuthServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException, DBManagerException {

        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");

        StringBuilder sb = new StringBuilder();
        //validate user in system
        response.sendRedirect("j_security_check?j_username="
                        + username + "&j_password=" + password);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                    HttpServletResponse response)
                    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DBManagerException ex) {
        }
    }


    @Override
    protected void doPost(HttpServletRequest request,
                    HttpServletResponse response)
                    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DBManagerException ex) {
        }
    }

}

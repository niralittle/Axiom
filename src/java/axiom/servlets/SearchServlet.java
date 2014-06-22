/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package axiom.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class SearchServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if ("user".equals(request.getAttribute("kind"))) response.sendRedirect("SearchUser.java");
        if ("startup".equals(request.getAttribute("kind"))) response.sendRedirect("SearchStartup.java");
        if ("vacancy".equals(request.getAttribute("kind"))) response.sendRedirect("SearchVacancy.java");
        return;
    }


}

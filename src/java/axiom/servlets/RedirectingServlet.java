package axiom.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void processRequest(HttpServletRequest request,
                HttpServletResponse response)
                throws ServletException, IOException {

		if(isAuthorized(request) == false) {
			redirect(request, response, "index.jsp");
		}
    }

	private boolean isAuthorized(HttpServletRequest request) {
		if(request.getUserPrincipal() != null) {
			return true;
		}
		return false;
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
	private void redirect(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		return;
	}


	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
                        throws ServletException, IOException {
		processRequest(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
                        throws ServletException, IOException {
		processRequest(request, response);
	}


	@Override
	public String getServletInfo() {
		return "LogoutServlet invalidates current session " +
                        "and logs the user out.";
	}

}

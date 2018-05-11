package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// import javax.servlet.http.Cookie;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: render the authentication page HTML
		request.getRequestDispatcher("/WEB-INF/admin-authentication.jsp").forward(request, response);
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle login
		String username = request.getParameter("uname");
		String password = request.getParameter("pwd");
		HttpSession session = request.getSession();
		UsersDAO dao = new UsersDAOImpl(session);
		if(dao.authenticate(username,password)){
			request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);
		}else{
			// request.setAttribute("error", "errors");
			// doGet(request,response);
			response.sendRedirect("auth");
		}
	}

    @Override
    public void doDelete( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle logout
				HttpSession session = request.getSession();
				UsersDAO dao = new UsersDAOImpl(session);
				dao.logout();
				// request.getRequestDispatcher("auth").forward(request, response);
				response.sendRedirect("auth");
				// doGet(request, response);
    }
}

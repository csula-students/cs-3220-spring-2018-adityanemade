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

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: render the authentication page HTML
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<head>");
		out.println("		<meta charset=\"UTF-8\">");
		out.println("		<title>Incremental Game</title>");
		out.println("		<link rel=\"stylesheet\" type=\"text/css\" href=\"../style.css\">");
		out.println("	</head>");
		out.println("	<body>");
		out.println("		<div class=\"main\">");
		out.println("			<div class=\"header\">");
		out.println("				<header><h1>Incremental Game Framework</h1></header>");
		out.println("			</div>");
		out.println("			<div class = \"flex\">");
		out.println("				<div class = \"flex-form\">");
		out.println("					<ul class = \"form\">");
		out.println("						<form method = \"POST\" id=\"auth-form\">");
		out.println("             <h3>Login</h3>");
		if(request.getAttribute("error") == "errors"){
			out.println("             <span id = \"error\">Incorrect Username/Password</span>");
		}
		out.println("							<li><label for = \"uname\">Username:</label></li>");
		out.println("							<li><input type=\"text\" id = \"uname\" name = \"uname\" required></li>");
		out.println("							<li><label for = \"pwd\">Password</label></li>");
		out.println("							<li><input type=\"password\" id=\"pwd\" name=\"pwd\" required></li>");
		out.println("							<li><button>Login</button></li>");
		out.println("						</form>");
		out.println("					</ul>");
		out.println("				</div>");
		out.println("			</div>");
		out.println("		</div>");
		out.println("	</body>");
		out.println("</html>");
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
			request.setAttribute("error", "errors");
			doGet(request,response);
			// response.sendRedirect("auth");
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

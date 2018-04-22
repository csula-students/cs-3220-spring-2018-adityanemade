package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gameinfo")
public class GameInfoServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		super.init(config);		 
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<head>");
		out.println("		<meta charset=\"UTF-8\">");
		out.println("		<title>Incremental Game</title>");
		out.println("		<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
		out.println("	</head>");
		out.println("	<body>");
		out.println("		<div class=\"main\">");
		out.println("			<div class=\"header\">");
		out.println("				<header><h1>Incremental Game Framework</h1></header>");
		out.println("			</div>");
		out.println("			<div class=\"nav\">");
		out.println("				<nav>");
		out.println("					<a id=\"a1\" href=\"admin-info.html\">Game Information</a> |");
		out.println("					<a id=\"a2\" href=\"admin-generators.html\">Generators</a> |");
		out.println("					<a id=\"a3\" href=\"admin-events.html\">Events</a>");
		out.println("				</nav>");
		out.println("			</div>");
		out.println("			<div class = \"flex\">");
		out.println("				<div class = \"flex-form\">");
		out.println("					<ul class = \"form\">");
		out.println("						<li><h3>Game Name</h3></li>");
		out.println("							<form method = \"post\" action=\"gameinfo\">");
		out.println("								<input type=\"text\" name=\"game_name\" id=\"game-name\">");
		if (request.getAttribute("nameError") != null) {
    			out.println("<span class = \"error\">* Enter name</span>");
    		}
		out.println("								<li><input type=\"submit\" id=\"btn_save\" name=\"btn_save\" value=\"Save\"></li>");
		out.println("							</form>");
		out.println("					</ul>");
		out.println("				</div>");
		out.println("			</div>");
		out.println("		</div>");
		out.println("	</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameName = request.getParameter("game_name");
		if ("".equals(gameName) || gameName == null) {
			request.setAttribute("nameError", true);
			doGet(request, response);			
		}else{
			System.out.println(gameName);
			doGet(request, response);
		}
	}
}

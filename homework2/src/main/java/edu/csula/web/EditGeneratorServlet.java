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

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

@WebServlet("/admin/editGenerator")
public class EditGeneratorServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO: render the events page HTML
			HttpSession session = request.getSession();
			UsersDAO userDao = new UsersDAOImpl(session);
			if (userDao.getAuthenticatedUser().isPresent()) {
				GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		  	Collection<Generator> generators = dao.getAll();
				int id = Integer.parseInt(request.getParameter("set_Id"));
		    Generator generator = null;
				for (Generator g : generators){
					if (g.getId() == id){
						generator = g;
					}
				}
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
				out.println("				<form method = \"DELETE\" id = \"logout-form\" actions = \"auth\" >");
				out.println("					<button id=\"logout-btn\">Logout</button>");
				out.println("				</form>");
				out.println("			</div>");
				out.println("			<div class = \"flex\">");
				out.println("				<div class = \"flex-form\">");
				out.println("					<ul class = \"form\">");
				out.println("						<a class=\"link_buttons\" href=\"generators\">Back</a>");
		    out.println("						<form method = \"POST\" id=\"edit-generators\">");
		    out.println("             <h3>Add Generator</h3>");
		    out.println("							<li><label for = \"generator_name\">Generator Name:</label></li>");
		    out.println("							<li><input type=\"text\" id = \"generator_name\" name = \"generator_name\" value="+ generator.getName() + " required></li>");
		    out.println("							<li><label for = \"generator_rate\">Generator Rate:</label></li>");
		    out.println("							<li><input type=\"text\" id = \"generator_rate\" name = \"generator_rate\" value="+ generator.getRate() + " required></li>");
		    out.println("							<li><label for = \"generator_base\">Base Cost:</label></li>");
		    out.println("							<li><input type=\"text\" id = \"generator_base\" name = \"generator_base\"value="+ generator.getBaseCost() + " required></li>");
		    out.println("							<li><label for = \"generator_unlock\">Unlock at:</label></li>");
		    out.println("							<li><input type=\"text\" id = \"generator_unlock\" name = \"generator_unlock\" value="+ generator.getUnlockAt() + " required></li>");
		    out.println("							<li><label for = \"description\">Event Description</label></li>");
		    out.println("							<li><textarea id = \"generator_description\" name=\"generator_description\" required>"+ generator.getDescription() +"</textarea></li>");
		    out.println("							<li><button>Edit</button></li>");
		    out.println("						</form>");
				out.println("					</ul>");
				out.println("				</div>");
				out.println("			</div>");
				out.println("		</div>");
				out.println("	</body>");
				out.println("</html>");
			}else{
				response.sendRedirect("auth");
			}
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
    GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
  	Collection<Generator> generators = dao.getAll();
    String name = request.getParameter("generator_name");
		int rate = Integer.parseInt(request.getParameter("generator_rate"));
		int base = Integer.parseInt(request.getParameter("generator_base"));
		int unlock = Integer.parseInt(request.getParameter("generator_unlock"));
		String description = request.getParameter("generator_description");
		Generator generator = new Generator(generators.size(), name , description , rate, base , unlock);
    int id = Integer.parseInt(request.getParameter("set_Id"));
		dao.set(id,generator);
		response.sendRedirect("generators");
	}
}

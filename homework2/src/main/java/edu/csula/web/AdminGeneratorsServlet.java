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

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: render the generators page HTML
		HttpSession session = request.getSession();
		UsersDAO userDao = new UsersDAOImpl(session);
		if (userDao.getAuthenticatedUser().isPresent()) {
			GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
			Collection<Generator> generators = dao.getAll();
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
			out.println("			<div class=\"nav\">");
			out.println("				<nav>");
			out.println("					<a id=\"a1\" href=\"admin-info.html\">Game Information</a> |");
			out.println("					<a id=\"a2\" href=\"generators\">Generators</a> |");
			out.println("					<a id=\"a3\" href=\"events\">Events</a>");
			out.println("				</nav>");
			out.println("			</div>");
			out.println("			<div class = \"flex\">");
			out.println("				<div class = \"flex-form\">");
			out.println("					<ul class = \"form\">");
			out.println("						<form method = \"POST\" id=\"generators_form\">");
			out.println("             <h3>Add Generator</h3>");
			out.println("							<li><label for = \"generator_name\">Generator Name:</label></li>");
			out.println("							<li><input type=\"text\" id = \"generator_name\" name = \"generator_name\" required></li>");
			out.println("							<li><label for = \"generator_rate\">Generator Rate:</label></li>");
			out.println("							<li><input type=\"text\" id = \"generator_rate\" name = \"generator_rate\" required></li>");
			out.println("							<li><label for = \"generator_base\">Base Cost:</label></li>");
			out.println("							<li><input type=\"text\" id = \"generator_base\" name = \"generator_base\" required></li>");
			out.println("							<li><label for = \"generator_unlock\">Unlock at:</label></li>");
			out.println("							<li><input type=\"text\" id = \"generator_unlock\" name = \"generator_unlock\" required></li>");
			out.println("							<li><label for = \"description\">Event Description</label></li>");
			out.println("							<li><textarea id = \"generator_description\" name=\"generator_description\" required></textarea></li>");
			out.println("							<li><button>{Add|Edit}</button></li>");
			out.println("						</form>");
			out.println("					</ul>");
			out.println("				</div>");
			out.println("				<div class = \"table\">");
			out.println("					<table>");
			out.println("						<tr>");
			out.println("							<th>Name</th>");
			out.println("							<th>Rate</th>");
			out.println("							<th>Cost</th>");
			out.println("							<th>Unlock at</th>");
			out.println("							<th>Description</th>");
			out.println("						</tr>");
			for(Generator generator: generators){
				out.println("					<tr>");
					out.println("					<td>"+generator.getName()+"</td>");
					out.println("					<td>"+generator.getRate()+"</td>");
					out.println("					<td>"+generator.getBaseCost()+"</td>");
					out.println("					<td>"+generator.getUnlockAt()+"</td>");
					out.println("					<td>"+generator.getDescription()+"</td>");
					out.println("					<td><a class = \"link_buttons\" href=\"editGenerator?set_Id=" + generator.getId() +"\">Edit</a>");
	        out.println("            |<a class = \"link_buttons\" href=\"delete?delete_Id=" + generator.getId() +"\">Delete</a></td>");
				out.println("					</tr>");
			}
			out.println("					</table>");
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
		dao.add(generator);
		response.sendRedirect("generators");
	}
}

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

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		out.println("<html>");
		out.println("	<head>");
		out.println("		<meta charset=\"UTF-8\">");
		out.println("		<title>Incremental Game</title>");
		out.println("		<link rel=\"stylesheet\" type=\"text/css\" href=\"../style.css\">");
		out.println("		<script type=\"text/javascript\" src=\"../custom-validation.js\"></script>");
		out.println("	</head>");
		out.println("	<body>");
		out.println("		<div class=\"main\">");
		out.println("			<div class=\"header\">");
		out.println("				<header><h1>Incremental Game Framework</h1></header>");
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
		out.println("						<form method = \"POST\" id=\"events_form\">");
    out.println("             <h3>Add Event</h3>");
		// if (request.getAttribute("error") != null) {
    // 			out.println("<span class = \"error\">* Missed out fields</span>");
    // 		}
		out.println("							<li><label for = \"event_name\">Event Name:</label></li>");
		out.println("							<li><input type=\"text\" id = \"event_name\" name = \"event_name\" value=\"\" required></li>");
		out.println("							<li><label for = \"description\">Event Description</label></li>");
		out.println("							<li><textarea id = \"event_description\" name=\"event_description\" value=\"\" required></textarea></li>");
		out.println("							<li><label for = \"trigger\">Trigger at</label></li>");
		out.println("							<li><input type=\"number\" id=\"trigger\" name=\"trigger\" value=\"\" required></li>");
		out.println("							<li><button>Add</button></li>");
		out.println("						</form>");
		out.println("					</ul>");
		out.println("				</div>");
		out.println("				<div class = \"table\">");
		out.println("					<table>");
		out.println("						<tr>");
		out.println("							<th>Name</th>");
		out.println("							<th>Description</th>");
		out.println("							<th>Trigger At</th>");
    out.println("							<th>Actions</th>");
		out.println("						</tr>");
		for(Event event: events){
			out.println("					<tr>");
				out.println("					<td>"+event.getName()+"</td>");
				out.println("					<td>"+event.getDescription()+"</td>");
				out.println("					<td>"+event.getTriggerAt()+"</td>");
        out.println("					<td><a class = \"link_buttons\" href=\"editEvent?set_Id=" + event.getId() +"\">Edit</a>");
        out.println("            |<a class = \"link_buttons\" href=\"deleteEvent?delete_Id=" + event.getId() +"\">Delete</a></td>");
			out.println("					</tr>");
		}
		out.println("					</table>");
		out.println("				</div>");
		out.println("			</div>");
		out.println("		</div>");
		out.println("	</body>");
		out.println("</html>");
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		String event_name = request.getParameter("event_name");
		String description = request.getParameter("event_description");
		int trigger = Integer.parseInt(request.getParameter("trigger"));
		// if (event_name == null && description == null && trigger == null ) {
		// 	request.setAttribute("error", true);
		// 	doGet(request, response);
		// }

		Event event = new Event(events.size(),event_name, description, trigger);
		dao.add(event);
		response.sendRedirect("events");
	}
}

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

@WebServlet("/admin/editEvent")
public class EditEventServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    EventsDAO dao = new EventsDAOImpl(getServletContext());
  	Collection<Event> events = dao.getAll();
		int id = Integer.parseInt(request.getParameter("set_Id"));
    Event event = null;
		for (Event e : events){
			if (e.getId() == id){
				event = e;
			}
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
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
		out.println("						<td><a class=\"link_buttons\" href=\"events\">Back</a>");
		out.println("						<form method = \"POST\" id=\"edit_events\">");
    out.println("             <h3>Edit Event</h3>");
		// if (request.getAttribute("error") != null) {
    // 			out.println("<span class = \"error\">* Missed out fields</span>");
    // 		}
		out.println("							<li><label for = \"event_name\">Event Name:</label></li>");
		out.println("							<li><input type=\"text\" id = \"event_name\" name = \"event_name\" value="+ event.getName() + "></li>");
		out.println("							<li><label for = \"description\">Event Description</label></li>");
		out.println("							<li><textarea id = \"description\" name=\"description\">"+ event.getDescription() +"</textarea></li>");
		out.println("							<li><label for = \"trigger\">Trigger at</label></li>");
		out.println("							<li><input type=\"number\" id=\"trigger\" name=\"trigger\" value="+ event.getTriggerAt() + "></li>");
		out.println("							<li><button>Add</button></li>");
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
		// TODO: handle upsert transaction
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		String event_name = request.getParameter("event_name");
		String description = request.getParameter("description");
		int trigger = Integer.parseInt(request.getParameter("trigger"));
    int id = Integer.parseInt(request.getParameter("set_Id"));
		// if (event_name == null && description == null && trigger == null ) {
		// 	request.setAttribute("error", true);
		// 	doGet(request, response);
		// }

		Event event = new Event(id,event_name, description, trigger);
		dao.set(id,event);
		response.sendRedirect("events");
	}
}

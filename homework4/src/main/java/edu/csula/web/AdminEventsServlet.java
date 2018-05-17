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

import edu.csula.storage.mysql.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

import edu.csula.storage.mysql.Database;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UsersDAO userDao = new UsersDAOImpl(session);
		if (userDao.getAuthenticatedUser().isPresent()) {
			EventsDAO eventDao = new EventsDAOImpl(new Database());
			Collection<Event> events = eventDao.getAll();
			request.setAttribute("eventData", events);
			request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);
		}else{
			response.sendRedirect("auth");
		}
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		EventsDAO eventDao = new EventsDAOImpl(new Database());
		Collection<Event> events = eventDao.getAll();
		String event_name = request.getParameter("event_name");
		String description = request.getParameter("event_description");
		int trigger = Integer.parseInt(request.getParameter("trigger"));
		Event event = new Event(events.size(),event_name, description, trigger);
		eventDao.add(event);
		response.sendRedirect("events");
	}
}

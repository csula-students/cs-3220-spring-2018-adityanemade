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

@WebServlet("/admin/editEvent")
public class EditEventServlet extends HttpServlet {
	private int id;

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO: render the events page HTML
			HttpSession session = request.getSession();
			UsersDAO userDao = new UsersDAOImpl(session);
			id = Integer.parseInt(request.getParameter("set_Id"));
			if (userDao.getAuthenticatedUser().isPresent()) {
				EventsDAO dao = new EventsDAOImpl(new Database());
		  	Collection<Event> events = dao.getAll();

		    Event event = null;
				for (Event e : events){
					if (e.getId() == id){
						event = e;
					}
				}
				request.setAttribute("eventEdit", event);
  			request.getRequestDispatcher("/WEB-INF/edit-event.jsp").forward(request, response);
			}else{
				response.sendRedirect("auth");
			}
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		EventsDAO dao = new EventsDAOImpl(new Database());
		Collection<Event> events = dao.getAll();
		String event_name = request.getParameter("event_name");
		String description = request.getParameter("description");
		int trigger = Integer.parseInt(request.getParameter("trigger"));
    // int id = Integer.parseInt(request.getParameter("set_Id"));
		Event event = new Event(id,event_name, description, trigger);
		dao.set(id,event);
		response.sendRedirect("events");
	}
}

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

@WebServlet("/admin/deleteEvent")
public class DeleteEventServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    EventsDAO dao = new EventsDAOImpl(getServletContext());
  	Collection<Event> events = dao.getAll();
    int id = Integer.parseInt(request.getParameter("delete_Id"));
    dao.remove(id);
		// response.sendRedirect("events");
		response.sendRedirect("events");
	}
}

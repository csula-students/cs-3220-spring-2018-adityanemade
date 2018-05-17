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

import edu.csula.storage.mysql.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

import edu.csula.storage.mysql.Database;

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UsersDAO userDao = new UsersDAOImpl(session);
		if (userDao.getAuthenticatedUser().isPresent()) {
			int id = Integer.parseInt(request.getParameter("delete_Id"));
			String ref = request.getHeader("referer");
			if(ref.contains("events")){
				EventsDAO eventDao = new EventsDAOImpl(new Database());
		  	Collection<Event> events = eventDao.getAll();
				eventDao.remove(id);
				response.sendRedirect("events");
			}
			if(ref.contains("generators")){
				GeneratorsDAO generatorDao = new GeneratorsDAOImpl(new Database());
		  	Collection<Generator> generators = generatorDao.getAll();
				generatorDao.remove(id);
				response.sendRedirect("generators");
			}
		}else{
			response.sendRedirect("auth");
		}
	}
}

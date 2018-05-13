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


@WebServlet("/admin/edit")
public class EditServlet extends HttpServlet {
  private int id;

  @Override
  public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
		UsersDAO userDao = new UsersDAOImpl(session);
		if (userDao.getAuthenticatedUser().isPresent()) {
      int id = Integer.parseInt(request.getParameter("set_Id"));
			String ref = request.getHeader("referer");
      if(ref.contains("events")){
				EventsDAO eventDao = new EventsDAOImpl(new Database());
		  	Collection<Event> events = eventDao.getAll();
        Event event = null;
        for (Event e : events){
					if (e.getId() == id){
						event = e;
					}
				}
        request.setAttribute("eventEdit", event);
  			request.getRequestDispatcher("/WEB-INF/edit-event.jsp").forward(request, response);
			}
			if(ref.contains("generators")){
				GeneratorsDAO generatorDao = new GeneratorsDAOImpl(new Database());
		  	Collection<Generator> generators = generatorDao.getAll();
        Generator generator = null;
				for (Generator g : generators){
					if (g.getId() == id){
						generator = g;
					}
				}
        request.setAttribute("generatorEdit", generator);
  			request.getRequestDispatcher("/WEB-INF/edit-generator.jsp").forward(request, response);
			}
    }else{
      response.sendRedirect("auth");
    }
  }

  @Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String ref = request.getParameter("editForm");
    if("editEventForm".equals(ref)){
      EventsDAO eventDao = new EventsDAOImpl(new Database());
  		Collection<Event> events = eventDao.getAll();
  		String event_name = request.getParameter("event_name");
  		String description = request.getParameter("description");
  		int trigger = Integer.parseInt(request.getParameter("trigger"));
      // int id = Integer.parseInt(request.getParameter("set_Id"));
  		Event event = new Event(id,event_name, description, trigger);
      eventDao.set(id,event);
  		response.sendRedirect("events");
    }
    if("editGeneratorForm".equals(ref)){
      GeneratorsDAO generatorDao = new GeneratorsDAOImpl(new Database());
    	Collection<Generator> generators = generatorDao.getAll();
      String name = request.getParameter("generator_name");
  		int rate = Integer.parseInt(request.getParameter("generator_rate"));
  		int base = Integer.parseInt(request.getParameter("generator_base"));
  		int unlock = Integer.parseInt(request.getParameter("generator_unlock"));
  		String description = request.getParameter("generator_description");
      // int id = Integer.parseInt(request.getParameter("set_Id"));
  		Generator generator = new Generator(id, name , description , rate, base , unlock);
  		generatorDao.set(id,generator);
  		response.sendRedirect("generators");
    }
  }
}

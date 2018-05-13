package edu.csula.web;

import java.util.Collection;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.mysql.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import edu.csula.storage.mysql.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.models.State;

import edu.csula.storage.mysql.Database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/game")
public class GameServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    EventsDAO eventsDao = new EventsDAOImpl(new Database());
		Collection<Event> events = eventsDao.getAll();
		GeneratorsDAO generatorsDao = new GeneratorsDAOImpl(new Database());
		Collection<Generator> generators = generatorsDao.getAll();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String state = gson.toJson(new State(events, generators));
		// System.out.println(state);
		request.setAttribute("state", state);
		request.setAttribute("lastGeneratorIndex", generators.size() - 1);
		request.getRequestDispatcher("/WEB-INF/game.jsp").forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}

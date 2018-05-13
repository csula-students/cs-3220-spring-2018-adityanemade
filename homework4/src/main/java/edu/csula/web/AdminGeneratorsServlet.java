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

import edu.csula.storage.mysql.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

import edu.csula.storage.mysql.Database;

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: render the generators page HTML
		HttpSession session = request.getSession();
		UsersDAO userDao = new UsersDAOImpl(session);
		if (userDao.getAuthenticatedUser().isPresent()) {
			GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
			Collection<Generator> generators = dao.getAll();
			request.setAttribute("generatorData", generators);
			request.getRequestDispatcher("/WEB-INF/admin-generator.jsp").forward(request, response);
		}else{
			response.sendRedirect("auth");
		}
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
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

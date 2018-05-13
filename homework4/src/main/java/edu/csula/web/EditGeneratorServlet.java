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

@WebServlet("/admin/editGenerator")
public class EditGeneratorServlet extends HttpServlet {
	private int id;

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO: render the events page HTML
			HttpSession session = request.getSession();
			UsersDAO userDao = new UsersDAOImpl(session);
			if (userDao.getAuthenticatedUser().isPresent()) {
				GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		  	Collection<Generator> generators = dao.getAll();
				id = Integer.parseInt(request.getParameter("set_Id"));
		    Generator generator = null;
				for (Generator g : generators){
					if (g.getId() == id){
						generator = g;
					}
				}
				request.setAttribute("generatorEdit", generator);
  			request.getRequestDispatcher("/WEB-INF/edit-generator.jsp").forward(request, response);
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
		// Generator generator = new Generator(generators.size(), name , description , rate, base , unlock);
    // int id = Integer.parseInt(request.getParameter("set_Id"));
		Generator generator = new Generator(id, name , description , rate, base , unlock);
		dao.set(id,generator);
		response.sendRedirect("generators");
	}
}

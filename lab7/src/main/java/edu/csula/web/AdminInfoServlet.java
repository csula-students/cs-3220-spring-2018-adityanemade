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

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

@WebServlet("/admin/info")
public class AdminInfoServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    UsersDAO userDao = new UsersDAOImpl(session);
    if (userDao.getAuthenticatedUser().isPresent()) {
      request.getRequestDispatcher("/WEB-INF/admin-info.jsp").forward(request, response);
    }else{
      response.sendRedirect("auth");
    }
  }
  @Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String gameName = request.getParameter("game_name");
  }
}

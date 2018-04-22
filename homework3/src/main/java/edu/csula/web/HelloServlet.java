package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.csula.models.User;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		// out.println("<h1>Hello world!</h1>");

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		User u = new User(0, "username", "password");
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String jsonString = gson.toJson(u);

		out.println(jsonString);

	}
}

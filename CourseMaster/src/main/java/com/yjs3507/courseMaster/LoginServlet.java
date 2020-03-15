package com.yjs3507.courseMaster;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginUsername = "admin";
		String loginPassword = "admin";
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (username.equalsIgnoreCase(loginUsername) && password.equalsIgnoreCase(loginPassword)) {
			resp.sendRedirect("person");
		} else {
			RequestDispatcher request = req.getRequestDispatcher("login.jsp");
			request.include(req, resp);
		}
	}
}

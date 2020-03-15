package com.yjs3507.courseMaster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjs3507.courseMaster.dal.PersonDAL;
import com.yjs3507.hibernate.entity.DanceType;
import com.yjs3507.hibernate.entity.Level;
import com.yjs3507.hibernate.entity.Person;
import com.yjs3507.hibernate.entity.PersonLevel;
import com.yjs3507.hibernate.entity.Role;

@SuppressWarnings("serial")
public class PersonServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("person.jsp");
		req.setAttribute("roles", Role.values());
		req.setAttribute("levels", Level.values());
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		String phoneNumber = req.getParameter("phone_number");
		String email = req.getParameter("email");
		String role = req.getParameter("role");
		String lindyLevel = req.getParameter("lindyLevel");
		String soloLevel = req.getParameter("soloLevel");

		// TODO request validity
		PersonDAL dal = new PersonDAL();
		List<PersonLevel> levelList = new ArrayList<PersonLevel>();
		levelList.add(new PersonLevel(Level.valueOf(lindyLevel), DanceType.LINDY_HOP));
		levelList.add(new PersonLevel(Level.valueOf(soloLevel), DanceType.SOLO_CHARLESTON));
		Person person = new Person(firstName, lastName, phoneNumber, email, Role.valueOf(role), levelList);
		levelList.get(0).setPerson(person);
		levelList.get(1).setPerson(person);
		boolean success = dal.insertEntity(person);

		String message = "Kişi kaydedildi";
		String show = "show";
		if (!success) {
			message = "Kişi zaten var";
			show = "";
		}
		req.setAttribute("message", message);
		req.setAttribute("show", show);
		this.doGet(req, resp);
	}

}
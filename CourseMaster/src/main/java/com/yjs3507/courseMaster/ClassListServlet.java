package com.yjs3507.courseMaster;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjs3507.courseMaster.dal.ClassesDAL;
import com.yjs3507.hibernate.entity.ClassStatus;
import com.yjs3507.hibernate.entity.Classes;

@SuppressWarnings("serial")
public class ClassListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/classList.jsp");
		ClassesDAL dal = new ClassesDAL();
		List<Classes> classes = dal.findAllActiveClasses();
		req.setAttribute("classList", classes);
	    dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long classId = Long.valueOf(req.getParameter("class_id"));
		boolean success = true;
		if(classId != null && classId != 0L) {
			ClassesDAL cDal = new ClassesDAL(); 
			Classes classes = cDal.findEntityById(classId);
			classes.setClassStatus(ClassStatus.PASSIVE);
			success = cDal.updateEntity(classes);
		}
		System.out.println(success);
		String message = "Sınıf kapatıldı";
		String show = "show";
		if (!success) {
			message = "Sınıf kapatılamadı";
			show = "";
		}
		req.setAttribute("message", message);
		req.setAttribute("show", show);
		this.doGet(req, resp);
	}
	
}
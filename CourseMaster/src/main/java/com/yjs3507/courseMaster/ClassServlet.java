package com.yjs3507.courseMaster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.util.StringUtils;
import com.yjs3507.courseMaster.dal.ClassesDAL;
import com.yjs3507.courseMaster.dal.PersonDAL;
import com.yjs3507.hibernate.entity.ClassMember;
import com.yjs3507.hibernate.entity.Classes;
import com.yjs3507.hibernate.entity.DanceType;
import com.yjs3507.hibernate.entity.Level;
import com.yjs3507.hibernate.entity.Person;
import com.yjs3507.hibernate.entity.Role;

@SuppressWarnings("serial")
public class ClassServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/class.jsp");


		req.setAttribute("types", DanceType.values());
		req.setAttribute("levels", Level.values());
		
	    String classLevel = req.getParameter("classLevel"); // Value of parent DD to find associated child DD options for.
	    String danceType = req.getParameter("danceType");

	    
	    req.setAttribute("danceType", danceType);
		req.setAttribute("classLevel", classLevel);
	    
	    if(!StringUtils.isNullOrEmpty(classLevel) && !StringUtils.isNullOrEmpty(danceType)) {
	    	
    		PersonDAL dal = new PersonDAL();		

    		//Sınıfa kaydolabilecek öğrenciler o sınıfın seviyesinde olmalıdır. Lindy hop sınıfında follow ve lead olarak iki ayrı rol bulunduğundan bunlar ayrı olarak kaydedilir
	    	if(DanceType.valueOf(danceType) == DanceType.LINDY_HOP) {
		    	List<Person> followers = dal.findPersonFilteredByLevelAndDanceType(Level.valueOf(classLevel), DanceType.valueOf(danceType), Role.FOLLOWER);
		    	List<Person> leaders = dal.findPersonFilteredByLevelAndDanceType(Level.valueOf(classLevel),DanceType.valueOf(danceType), Role.LEADER);
		    	req.setAttribute("followers",followers);
		    	req.setAttribute("leaders", leaders);
	    	} else {
	    		List<Person> solo = dal.findPersonFilteredByLevelAndDanceType(Level.valueOf(classLevel), DanceType.valueOf(danceType), null);
	    		req.setAttribute("solo",solo);
 	    	}
	    }
	    
	    dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String danceType = req.getParameter("dance_type");
		String classLevel =req.getParameter("class_level");
		Date startDate =  new Date(req.getParameter("datetimepicker1"));
		
		Classes classes = new Classes(DanceType.valueOf(danceType),Level.valueOf(classLevel),0,startDate);
		
		List<ClassMember> memberList = new ArrayList<>();
		PersonDAL personDal = new PersonDAL();
		ClassesDAL classesDal = new ClassesDAL();
		if(DanceType.valueOf(danceType) == DanceType.LINDY_HOP) {
			String[] followers = req.getParameterValues("followers");
			String[] leaders = req.getParameterValues("leaders");
			
			for(int i=0; i<followers.length; i++) {
				memberList.add(new ClassMember(classes,Role.FOLLOWER,personDal.findEntityById(Long.valueOf(followers[i]))));
			}
			for(int i=0; i<leaders.length; i++) {
				memberList.add(new ClassMember(classes,Role.LEADER,personDal.findEntityById(Long.valueOf(leaders[i]))));
			}
		} else {
			String[] solos = req.getParameterValues("solos");
			for(int i=0; i<solos.length; i++) {
				memberList.add(new ClassMember(classes,Role.UNKNOWN,personDal.findEntityById(Long.valueOf(solos[i]))));
			}
		}
		
		classes.setClassMembers(memberList);
		
		boolean success  = classesDal.insertEntity(classes);
		
		String message = "Sınıf kaydedildi";
		String show = "show";
		if (!success) {
			message = "Sınıf zaten var";
			show = "";
		}
		req.setAttribute("message", message);
		req.setAttribute("show", show);
		this.doGet(req, resp);
	}

}

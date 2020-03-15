package com.yjs3507.courseMaster;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjs3507.courseMaster.dal.AttendanceDAL;
import com.yjs3507.courseMaster.dal.AttendancePersonDAL;
import com.yjs3507.courseMaster.dal.ClassMemberDAL;
import com.yjs3507.courseMaster.dal.ClassesDAL;
import com.yjs3507.courseMaster.dal.PersonDAL;
import com.yjs3507.courseMaster.dal.PersonLevelDAL;
import com.yjs3507.hibernate.entity.Attendance;
import com.yjs3507.hibernate.entity.AttendancePerson;
import com.yjs3507.hibernate.entity.ClassMember;
import com.yjs3507.hibernate.entity.ClassStatus;
import com.yjs3507.hibernate.entity.Classes;
import com.yjs3507.hibernate.entity.PersonLevel;

@SuppressWarnings("serial")
public class AttendanceServlet extends HttpServlet {

	/**
	 * Sınıfın ders no en son yoklaması alınan dersi gösterir. Yoklama ekranı sınıf üzerinde gözüken ders sayısından bir sonraki ders için alınmaktadır
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("attendance.jsp");
		Long classId = Long.valueOf(req.getParameter("classId"));
		//ekranda yoklamanın hani sınıf için alındığı gösterilmesi için
		if (classId != null || classId != 0) {
			ClassesDAL dal = new ClassesDAL();
			Classes classes = dal.getClassWithMembersById(classId);
			if (classes.getLessonNumber() == 5 && classes.getDanceType().getMaxLevel() == classes.getClassLevel()) {
				classes.setLessonNumber(classes.getLessonNumber() + 1);
				classes.setClassStatus(ClassStatus.PASSIVE);
			} else if (classes.getLessonNumber() == 6) {
				classes.setClassLevel(classes.getClassLevel().getPostLevel());
				classes.setLessonNumber(1);
			} else {
				classes.setLessonNumber(classes.getLessonNumber() + 1);
			}
			req.setAttribute("classes", classes);
		}

		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long classId = Long.valueOf(req.getParameter("classId"));
		boolean success = true;
		if (classId != null || classId != 0L) {
			ClassesDAL cDal = new ClassesDAL();
			Classes classes = cDal.findEntityById(classId);
			String[] attendents = req.getParameterValues("attend");
			boolean updatePersonLevel = false;
			//Eğer ekrandan en az bir katılımcı gelmişse ders olmuştur
			if (attendents != null && attendents.length > 0) {
				//Her bir sınıfın her bir seviye için 6 dersi vardır. Ayrıca her bir sınıfın max bir seviyesi vardır
				
				
				if (classes.getLessonNumber() == 5 && classes.getDanceType().getMaxLevel() == classes.getClassLevel()) {
					//Eğer sınıf ders no 5 ise 6. yoklama girilmektedir, bu durumda eğer sınıf son seviye dersi veriyorsa artık verilecek kur kalmamıştır sınıf kapanır
					classes.setLessonNumber(classes.getLessonNumber() + 1);
					classes.setClassStatus(ClassStatus.PASSIVE);
				} else if(classes.getLessonNumber() == 5) {
					//Eğer sınıf ders no 5 ise 6. yoklama girilmektedir, yukarıdaki ife girmediği için sınıfın bir sonraki kura geçmesi gerekmektedir Bu durumda sınıf üyelerinin 
					//güncel seviyeleri de bir sonraki seviye olarak güncellenmelidir
					updatePersonLevel = true;
					classes.setLessonNumber(classes.getLessonNumber() + 1);
				} else if (classes.getLessonNumber() == 6) {
					//Eğer sınıfın ders no 6 ise bir sonraki kurun ilk dersinin yoklaması alınmıştır, son yapılan ders bilgileri bu nedenle bir sonraki kurun ilk dersi olarak güncellenir.
					classes.setClassLevel(classes.getClassLevel().getPostLevel());
					classes.setLessonNumber(1);
				} else {
					classes.setLessonNumber(classes.getLessonNumber() + 1);
				}
				cDal.updateEntity(classes);
				AttendanceDAL aDal = new AttendanceDAL();
				Attendance attendance = new Attendance(classes, classes.getClassLevel(), classes.getLessonNumber(),
						new Date());
				success &= aDal.insertEntity(attendance);
				AttendancePersonDAL apDal = new AttendancePersonDAL();
				PersonDAL pDal = new PersonDAL();
				AttendancePerson attendancePerson = null;
				for (int i = 0; i < attendents.length; i++) {
					attendancePerson = new AttendancePerson(pDal.findEntityById(Long.valueOf(attendents[i])),
							attendance);
					success &= apDal.insertEntity(attendancePerson);
				}
				
				if(updatePersonLevel) {
					//Eğer sınıf ders no 5 ise 6. yoklama girilmektedir, yukarıdaki ife girmediği için sınıfın bir sonraki kura geçmesi gerekmektedir Bu durumda sınıf üyelerinin 
					//güncel seviyeleri de bir sonraki seviye olarak güncellenmelidir
					ClassMemberDAL cmDAL = new ClassMemberDAL(); 
					PersonLevelDAL plDAL = new PersonLevelDAL();
					System.out.println(classes.getClassLevel());
					Map<Long,Long> atdCountMap = aDal.findAttendanceCount(classes.getId());
					List<ClassMember> cmList = cmDAL.findClassMembersByClassId(classes.getId());
					for(ClassMember classMember : cmList) {
						//Eğer bir öğrenci mininum 3 derse gelmemiş ise sınıf tekrarı yapmak zorundadır
						if(atdCountMap.containsKey(classMember.getPerson().getId()) && atdCountMap.get(classMember.getPerson().getId()) > 2) {
							for(PersonLevel personLevel : classMember.getPerson().getPersonLevel()) {
								//min 3 derse katılmış öğrencinin o dans tipi için seviyesi güncellenir.
								if(personLevel.getDanceType() == classes.getDanceType()) {
									personLevel.setLevel(classes.getClassLevel().getPostLevel());
									plDAL.updateEntity(personLevel);
								}
							}
						} else {
							//3 dersten daha az katılımı olan öğrenci sınıf listesinden çıkarılır
							cmDAL.deleteEntity(classMember);
						}
					}
				}
				
			}

		}

		String message = "Yoklama kaydedildi";
		String show = "show";
		if (!success) {
			message = "Yoklama zaten var";
			show = "";
		}
		req.setAttribute("message", message);
		req.setAttribute("show", show);
		resp.sendRedirect("classList");

	}
	
}

package com.yjs3507.courseMaster.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yjs3507.hibernate.config.DatabaseManager;
import com.yjs3507.hibernate.entity.Attendance;

public class AttendanceDAL extends AbstractDAL<Attendance> {

	public AttendanceDAL() {
		super(Attendance.class);
	}
	
	public Map<Long, Long> findAttendanceCount(long classId) {
		Session session = null;
		Transaction transaction = null;
		Map<Long,Long> resultMap = new HashMap();
		
		String query = "select count(*), person.id from Attendance attendance "
				+ "left join attendance.attendancePerson attendancePerson "
				+ "left join attendancePerson.person person "
				+ "left join attendance.classes classes "
				+ "where attendance.level = classes.classLevel and classes.id = :classId "
				+ "group by person.id";
		
		try {
			session = DatabaseManager.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			List<Object[]> resultList = session.createQuery(query).setParameter("classId", classId).list();
			transaction.commit();
			
			if(resultList != null) {
				for(Object[] result : resultList) {
					resultMap.put((Long)result[1], (Long)result[0]);
				}
			}
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			if (transaction != null)
				transaction.rollback();
			
		} finally {
			if (session != null)
				session.close();
		}

		return resultMap;
	}

}

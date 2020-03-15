package com.yjs3507.courseMaster;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.yjs3507.hibernate.config.DatabaseManager;

public class CourseMasterTest {

	@Test
	public void testDatabaseSession() {
		Session session = DatabaseManager.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		session.close();
		DatabaseManager.getSessionFactory().close();
	}

}

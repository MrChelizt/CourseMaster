package com.yjs3507.courseMaster.dal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yjs3507.hibernate.config.DatabaseManager;
import com.yjs3507.hibernate.entity.Classes;

public class ClassesDAL extends AbstractDAL<Classes> {

	public ClassesDAL() {
		super(Classes.class);
	}

	@SuppressWarnings("unchecked")
	public List<Classes> findAllActiveClasses() {
		Session session = null;
		Transaction transaction = null;
		List<Classes> classes = null;

		String query = "select classes from Classes classes " + "where classStatus = 'ACTIVE'";

		try {
			session = DatabaseManager.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			classes = session.createQuery(query).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.err.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return classes;

	}

	public Classes getClassWithMembersById(long id) {
		Session session = null;
		Transaction transaction = null;
		Classes classes = null;

		String query = "select classes from Classes classes " + "left join fetch classes.classMembers classMembers "
				+ "left join fetch classMembers.person person " + "where classes.id = :id";

		System.out.println("query" + query);

		try {
			session = DatabaseManager.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			classes = (Classes) session.createQuery(query).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.err.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return classes;
	}
}

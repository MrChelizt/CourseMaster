package com.yjs3507.courseMaster.dal;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.yjs3507.hibernate.config.DatabaseManager;
import com.yjs3507.hibernate.entity.GenericEntity;

public abstract class AbstractDAL<E extends GenericEntity> implements GenericDAL<E> {
	private final Class<?> clazz;

	public AbstractDAL(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean insertEntity(E entity) {
		boolean result = false;
		Session session = null;
		Transaction transaction = null;

		try {
			session = DatabaseManager.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
			result = true;

		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.err.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return result;
	}

	@Override
	public boolean updateEntity(E entity) {
		boolean result = false;
		Session session = null;
		Transaction transaction = null;

		try {
			session = DatabaseManager.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
			result = true;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.err.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return result;
	}

	@Override
	public boolean deleteEntity(E entity) {
		boolean result = false;
		Session session = null;
		Transaction transaction = null;

		try {
			session = DatabaseManager.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();
			result = true;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.err.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return result;
	}

	@Override
	public List<E> getAllEntity() {
		List<E> entities = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = DatabaseManager.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<E> createQuery = (CriteriaQuery<E>) criteriaBuilder.createQuery(clazz);
			Root<E> entryRoot = (Root<E>) createQuery.from(clazz);
			CriteriaQuery<E> select = createQuery.select(entryRoot);
			Query<E> allQuery = session.createQuery(select);
			entities = allQuery.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.err.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return entities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E findEntityById(long id) {
		Session session = null;
		Transaction transaction = null;
		try {
			System.out.println(clazz);
			session = DatabaseManager.getSessionFactory().openSession();
			return (E) session.get(clazz, id);

		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.err.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return null;
	}

}

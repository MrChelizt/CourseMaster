package com.yjs3507.courseMaster.dal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.yjs3507.hibernate.config.DatabaseManager;
import com.yjs3507.hibernate.entity.DanceType;
import com.yjs3507.hibernate.entity.Level;
import com.yjs3507.hibernate.entity.Person;
import com.yjs3507.hibernate.entity.Role;

public class PersonDAL extends AbstractDAL<Person> {

	public PersonDAL() {
		super(Person.class);
	}

	@SuppressWarnings("unchecked")
	public List<Person> findPersonFilteredByLevelAndDanceType(Level level, DanceType danceType, Role role) {
		Session session = null;
		Transaction transaction = null;
		List<Person> persons = null;

		String query = "select person from Person person join fetch person.personLevel personLevel where personLevel.level = :level and personLevel.danceType = :danceType";

		if (danceType == DanceType.LINDY_HOP) {
			query += " and person.role = :role";
		}

		try {
			session = DatabaseManager.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query<Person> personQuery = session.createQuery(query).setParameter("level", level)
					.setParameter("danceType", danceType);
			if (danceType == DanceType.LINDY_HOP) {
				personQuery.setParameter("role", role);

			}

			persons = personQuery.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.err.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return persons;
	}
}

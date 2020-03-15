package com.yjs3507.courseMaster.dal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;

import com.yjs3507.hibernate.config.DatabaseManager;
import com.yjs3507.hibernate.entity.ClassMember;

public class ClassMemberDAL extends AbstractDAL<ClassMember> {

	public ClassMemberDAL() {
		super(ClassMember.class);
	}
	
	public List<ClassMember> findClassMembersByClassId(long classId){
		Session session = null;
		Transaction transaction = null;
		List<ClassMember> classMembers = new ArrayList<ClassMember>();

		String query = "select classMember from ClassMember classMember "
				+ "join fetch classMember.person person "
				+ "join fetch classMember.classes classes "
				+ "join fetch person.personLevel "
				+ "where classes.id = :classId";


		try {
			session = DatabaseManager.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			classMembers = session.createQuery(query).setParameter("classId", classId).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).getResultList();
			System.out.println("classMember size " + classMembers.size());
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.err.println(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return classMembers;
	}

}

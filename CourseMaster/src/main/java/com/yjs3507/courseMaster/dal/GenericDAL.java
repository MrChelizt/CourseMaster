package com.yjs3507.courseMaster.dal;

import java.util.List;

import com.yjs3507.hibernate.entity.GenericEntity;

public interface GenericDAL<E extends GenericEntity> {

	public abstract boolean insertEntity(E entity);

	public abstract boolean updateEntity(E entity);

	public abstract boolean deleteEntity(E entity);

	public abstract List<E> getAllEntity();

	public abstract E findEntityById(long id);

}

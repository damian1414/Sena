package com.prueba.ejb;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class AbstractFacade<T> {
	
	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	private Class<T> entityClass;
	
	protected abstract EntityManager getEntityManager();
	
	
	public void create(T entity){
		getEntityManager().persist(entity);
	}
	
	public void edit(T entity){
		getEntityManager().merge(entity);
	}

	
	public void remove(T entity){
		getEntityManager().remove(getEntityManager().merge(entity));
	}
	
	
	public T find(Object id){
		
		
		return getEntityManager().find(entityClass, id);
		
	}
	
	
	public List<T> findAll(){
		javax.persistence.criteria.CriteriaQuery eq= getEntityManager().getCriteriaBuilder().createQuery();
		eq.select(eq.from(entityClass));
		return getEntityManager().createQuery(eq).getResultList();
//		return this.getEntityManager().createNamedQuery(entityClass.getName()+".finAll()").getResultList();
	}

}

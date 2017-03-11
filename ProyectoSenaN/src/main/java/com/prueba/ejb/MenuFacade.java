package com.prueba.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.prueba.model.Menu;

@Stateless
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal{
	
	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public MenuFacade(){
		super(Menu.class);
	}
	
	@Override
	protected EntityManager getEntityManager(){
		return em;
	}

}

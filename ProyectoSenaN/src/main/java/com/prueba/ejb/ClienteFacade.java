package com.prueba.ejb;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.prueba.model.Cliente;
import com.prueba.model.Sucursal;

@Stateless
public class ClienteFacade  extends AbstractFacade<Cliente> implements ClienteFacadeLocal{

	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public ClienteFacade() {
		super(Cliente.class);
	}
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	

}

package com.prueba.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.prueba.model.Proveedor;


@Stateless
public class ProveedorFacade  extends AbstractFacade<Proveedor> implements ProveedorFacadeLocal{

	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public ProveedorFacade() {
		super(Proveedor.class);
	}
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	

}

package com.prueba.ejb;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.prueba.model.Sucursal;

@Stateless
public class SucursalFacade  extends AbstractFacade<Sucursal> implements SucursalFacadeLocal{

	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public SucursalFacade() {
		super(Sucursal.class);
	}
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	

}

package com.prueba.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.prueba.model.TipoProducto;


@Stateless
public class TipoProductoFacade  extends AbstractFacade<TipoProducto> implements TipoProductoFacadeLocal{

	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public TipoProductoFacade() {
		super(TipoProducto.class);
	}
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	

}

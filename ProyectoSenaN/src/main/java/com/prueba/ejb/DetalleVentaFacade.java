package com.prueba.ejb;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.prueba.model.DetalleVenta;
import com.prueba.model.ProductoVenta;
import com.prueba.model.Venta;

@Stateless
public class DetalleVentaFacade extends AbstractFacade<DetalleVenta> implements DetalleVentaFacadeLocal{

	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public DetalleVentaFacade() {
		super(DetalleVenta.class);
	}


	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}


	

}

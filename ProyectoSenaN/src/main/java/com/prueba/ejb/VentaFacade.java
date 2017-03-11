package com.prueba.ejb;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.prueba.model.Venta;

@Stateless
public class VentaFacade extends AbstractFacade<Venta> implements VentaFacadeLocal{

	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public VentaFacade() {
		super(Venta.class);
	}


	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}


	


	@Override
	public Venta procedimientoCrearVenta(int idEmpleado, int idCliente,
			int idSucursal) {
		// TODO Auto-generated method stub
		
//		StoredProcedureQuery query= em.createStoredProcedureQuery("PRODUCTS_ALL").registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
//		query.execute();
		List<Venta> ven= new ArrayList<Venta>();
//		StoredProcedureQuery storedProcedureQuery=em.createStoredProcedureQuery("SIB_PQ_SENA.CREAR_VENTAS",Venta.class);
//		storedProcedureQuery.registerStoredProcedureParameter("P_ID_EMPLEADO", Integer.class, ParameterMode.IN);
//		storedProcedureQuery.registerStoredProcedureParameter("P_ID_CLIENTE", Integer.class, ParameterMode.IN);
//		storedProcedureQuery.registerStoredProcedureParameter("P_ID_SUCURSAL", Integer.class, ParameterMode.IN);
//		storedProcedureQuery.registerStoredProcedureParameter("CURSORRETURN", void.class, ParameterMode.REF_CURSOR);
//		storedProcedureQuery.setParameter("P_ID_EMPLEADO", idEmpleado);
//		storedProcedureQuery.setParameter("P_ID_CLIENTE", idCliente);
//		storedProcedureQuery.setParameter("P_ID_SUCURSAL", idSucursal);
//		
//		
//		storedProcedureQuery.execute();
//		List s=storedProcedureQuery.getResultList();
		StoredProcedureQuery s= em.createNamedStoredProcedureQuery("crearVentas");
		s.setParameter("P_ID_EMPLEADO", idEmpleado);
		s.setParameter("P_ID_CLIENTE", idCliente);
		s.setParameter("P_ID_SUCURSAL", idSucursal);
		s.execute();
		
		List<Venta> ve = (List<Venta>) s.getResultList();

		return ve.get(0);
	}
	

}

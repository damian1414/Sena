package com.prueba.ejb;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.prueba.model.Empleado;


//@Named("categoriaFacade")
//@SessionScoped
@Stateless
public class EmpleadoFacade   extends AbstractFacade<Empleado> implements EmpleadoFacadeLocal{

	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public EmpleadoFacade() {
		super(Empleado.class);
	}
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}


	@Override
	public Empleado empleados(int id) {
		// TODO Auto-generated method stub
		Empleado empleadito = null;
		try {
			String jpql=" FROM Empleado e WHERE e.idEmpelado = ?1 ";
			Query q= em.createQuery(jpql);
			q.setParameter(1, id);
			empleadito=(Empleado) q.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return empleadito;
	}


	@Override
	public void eliminarEmpleado(int idEmpleado) {
		// TODO Auto-generated method stub
		String eliminarEmpleado;
		try {
			eliminarEmpleado= "Delete FROM  Empleado e WHERE e.idEmpelado = ?1 ";
			Query query = em.createQuery(eliminarEmpleado);
			query.setParameter(1, idEmpleado);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}

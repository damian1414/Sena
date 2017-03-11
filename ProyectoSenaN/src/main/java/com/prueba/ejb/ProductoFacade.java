package com.prueba.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.prueba.model.Empleado;
import com.prueba.model.Producto;


@Stateless
public class ProductoFacade  extends AbstractFacade<Producto> implements ProductoFacadeLocal{

	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public ProductoFacade() {
		super(Producto.class);
	}
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}


	@Override
	public void modificarProducto(Integer id, Integer cantidad) {
		// TODO Auto-generated method stub
		String modificarProducto;
		try {
			modificarProducto = "Update Producto p Set cantidad = ?1 WHERE p.idProducto = ?2 ";
			Query query = em.createQuery(modificarProducto);
			query.setParameter(1, cantidad);
			query.setParameter(2, id);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Override
	public List<Producto> listaProductosStock() {
		// TODO Auto-generated method stub
		
			List<Producto> listaProducto= new ArrayList<Producto>();
			try {
				String jpql=" FROM Producto p WHERE p.cantidad <> 0 ";
				Query q= em.createQuery(jpql);
				listaProducto=q.getResultList();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return listaProducto;
		}


	@Override
	public void modificarProductoEliminar(Integer id, Integer cantidad) {
		// TODO Auto-generated method stub
		
		String modificarProducto;
		try {
			modificarProducto = "Update Producto p Set cantidad = cantidad+?1 WHERE p.idProducto = ?2 ";
			Query query = em.createQuery(modificarProducto);
			query.setParameter(1, cantidad);
			query.setParameter(2, id);
			query.executeUpdate();
		} catch (Exception e) {
			// TOD
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error Transacion"));
		}
		
	}
	
	

}

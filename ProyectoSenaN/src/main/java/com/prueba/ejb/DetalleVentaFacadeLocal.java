package com.prueba.ejb;

import java.util.List;
import javax.ejb.Local;

import com.prueba.model.DetalleVenta;
import com.prueba.model.Producto;


@Local
public interface DetalleVentaFacadeLocal {
	
	void create(DetalleVenta detalleVenta);
	
	void edit(DetalleVenta detalleVenta);
	
	void remove(DetalleVenta detalleVenta);
	
	DetalleVenta find(Object id);
	
	List<DetalleVenta> findAll();
	
	
}

package com.prueba.ejb;

import java.util.List;

import javax.ejb.Local;

import com.prueba.model.Producto;
import com.prueba.model.Venta;


@Local
public interface VentaFacadeLocal {
	
	void create(Venta venta);
	
	void edit(Venta venta);
	
	void remove(Venta venta);
	
	Venta find(Object id);
	
	List<Venta> findAll();
	
	Venta procedimientoCrearVenta(int idEmpleado,int idCliente, int idSucursal);
		
}

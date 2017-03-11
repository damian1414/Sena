package com.prueba.ejb;

import java.util.List;

import javax.ejb.Local;

import com.prueba.model.Producto;
import com.prueba.model.ProductoVenta;
import com.prueba.model.Venta;


@Local
public interface ProductoVentaFacadeLocal {
	
	void create(ProductoVenta productoVenta);
	
	void edit(ProductoVenta productoVenta);
	
	void remove(ProductoVenta productoVenta);
	
	ProductoVenta find(Object id);
	
	List<ProductoVenta> findAll();
	
	ProductoVenta procedimientoCrearProductoVenta(int idEmpleado,int idCliente, int idSucursal);
		
}

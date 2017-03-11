package com.prueba.ejb;

import java.util.List;
import javax.ejb.Local;
import com.prueba.model.Producto;


@Local
public interface ProductoFacadeLocal {
	
	void create(Producto producto);
	
	void edit(Producto producto);
	
	void remove(Producto producto);
	
	Producto find(Object id);
	
	List<Producto> findAll();
	
	void modificarProducto(Integer id,Integer cantidad);
	
	List<Producto> listaProductosStock();
	
	void modificarProductoEliminar(Integer id,Integer cantidad);
	
}

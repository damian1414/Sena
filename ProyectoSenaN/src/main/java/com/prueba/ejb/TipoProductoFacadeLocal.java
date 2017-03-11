package com.prueba.ejb;

import java.util.List;

import javax.ejb.Local;
import com.prueba.model.TipoProducto;
@Local
public interface TipoProductoFacadeLocal {
	
	void create(TipoProducto tipoProducto);
	
	void edit(TipoProducto tipoProducto);
	
	void remove(TipoProducto tipoProducto);
	
	TipoProducto find(Object id);
	
	List<TipoProducto> findAll();
	
}

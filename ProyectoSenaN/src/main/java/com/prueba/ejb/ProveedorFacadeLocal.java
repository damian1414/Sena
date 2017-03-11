package com.prueba.ejb;

import java.util.List;

import javax.ejb.Local;

import com.prueba.model.Proveedor;
@Local
public interface ProveedorFacadeLocal {
	
	void create(Proveedor proveedor);
	
	void edit(Proveedor proveedor);
	
	void remove(Proveedor proveedor);
	
	Proveedor find(Object id);
	
	List<Proveedor> findAll();
	
}

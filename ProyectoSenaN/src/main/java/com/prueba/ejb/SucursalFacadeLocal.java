package com.prueba.ejb;
import java.util.List;
import javax.ejb.Local;
import com.prueba.model.Sucursal;




@Local
public interface SucursalFacadeLocal {
	
	void create(Sucursal sucursal);
	
	void edit(Sucursal sucursal);
	
	void remove(Sucursal sucursal);
	
	Sucursal find(Object id);
	
	List<Sucursal> findAll();
	
}

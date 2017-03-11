package com.prueba.ejb;

import java.util.List;
import javax.ejb.Local;
import com.prueba.model.Empleado;




@Local
public interface EmpleadoFacadeLocal {
	
	void create(Empleado empleado);
	
	void edit(Empleado empleado);
	
	void remove(Empleado empleado);
	
	Empleado find(Object id);
	
	List<Empleado> findAll();

	Empleado empleados(int id);
	
	void eliminarEmpleado(int idEmpleado);
}

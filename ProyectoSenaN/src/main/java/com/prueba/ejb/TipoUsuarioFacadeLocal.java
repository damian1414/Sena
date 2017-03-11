package com.prueba.ejb;

import java.util.List;
import javax.ejb.Local;
import com.prueba.model.TipoUsuario;



@Local
public interface TipoUsuarioFacadeLocal {
	
	void create(TipoUsuario tipoUsuario);
	
	void edit(TipoUsuario tipoUsuario);
	
	void remove(TipoUsuario tipoUsuario);
	
	TipoUsuario find(Object id);
	
	List<TipoUsuario> findAll();
	
}

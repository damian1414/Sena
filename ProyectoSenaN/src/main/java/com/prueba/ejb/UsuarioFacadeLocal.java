package com.prueba.ejb;

import java.util.List;

import javax.ejb.Local;
import com.prueba.model.Usuario;




@Local
public interface UsuarioFacadeLocal {
	
	void create(Usuario usuario);
	
	void edit(Usuario usuario);
	
	void remove(Usuario usuario);
	
	Usuario find(Object id);
	
	List<Usuario> findAll();
	
	Usuario iniciarSession(Usuario us);
	
	void editarUsuario(Usuario usuario);
	
}

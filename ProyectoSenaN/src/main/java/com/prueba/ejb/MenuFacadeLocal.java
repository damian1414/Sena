package com.prueba.ejb;

import java.util.List;

import javax.ejb.Local;
import com.prueba.model.Menu;




@Local
public interface MenuFacadeLocal {
	
	void create(Menu menu);
	
	void edit(Menu menu);
	
	void remove(Menu menu);
	
	Menu find(Object id);
	
	List<Menu> findAll();
}

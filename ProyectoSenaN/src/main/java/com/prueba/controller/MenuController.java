package com.prueba.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.prueba.ejb.MenuFacadeLocal;
import com.prueba.model.Menu;
import com.prueba.model.Usuario;

@ManagedBean
@SessionScoped
public class MenuController implements Serializable{
	
	@EJB
	private MenuFacadeLocal EJBmenu;
	
	private List<Menu> listaMenus;
	private  MenuModel model;
	
	@PostConstruct
	public void init(){
		this.listarMenus();
		model= new DefaultMenuModel();
		this.establecerPermisos();
	}
	
	public void listarMenus(){
	
		try {
			listaMenus=EJBmenu.findAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void establecerPermisos(){
		Usuario us= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		for (Menu m : listaMenus) {
			if(m.getTipo().equals("S") && m.getTipoUsuario()== us.getTipoUsuario().getIdTipoUsuario()){
				DefaultSubMenu firstSubmenu  = new DefaultSubMenu(m.getNombre());
			for (Menu i : listaMenus) {
				Menu submenu  =  i.getCodigoSubMenu();
				
				if(submenu != null ){
					if(submenu.getCodigoMenu() == m.getCodigoMenu()){
						DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
						item.setUrl(i.getUrl());
						firstSubmenu.addElement(item);
					}
				}
			}			
				model.addElement(firstSubmenu);
			}else{
				if(m.getCodigoSubMenu() == null && m.getTipoUsuario()== us.getTipoUsuario().getIdTipoUsuario()){
					DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
					item.setUrl(m.getUrl());
					model.addElement(item);
				}
				
			}
		}
		
	}
	
	public void cerrarSession(){
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
	
	public String mostrarUsuarioLogin(){
		
		Usuario us= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		return us.getUsuario();
	}
}

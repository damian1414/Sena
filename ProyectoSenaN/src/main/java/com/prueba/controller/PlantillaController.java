package com.prueba.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.prueba.model.Usuario;


@ManagedBean
@ViewScoped
public class PlantillaController implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7659111725450418955L;

	public void verificarSession(){
		try {
			FacesContext context= FacesContext.getCurrentInstance();
			Usuario usuario=(Usuario)context.getExternalContext().getSessionMap().get("usuario");
			if (usuario == null){
				context.getExternalContext().redirect("./../permisos.xhtml");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}

package com.prueba.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import com.prueba.ejb.UsuarioFacadeLocal;
import com.prueba.model.Usuario;

@ManagedBean
@ViewScoped
public class IndexController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioFacadeLocal usuarioEJBUsuario;
	
	private Usuario usuario;
	
	//@Inject
	@Inject
	private UsuarioController usuarioController;
	
	@Inject
	private ProductoController pr;
	
	

	@PostConstruct
	public void init(){
		
		usuario= new Usuario();
	}

	public String iniciarSession(){
		String redireccion= null;
		Usuario user;
		String clave;
		try {
			clave=usuario.getClave();
			//pr.cambiarAccion();
			usuario.setClave(usuarioController.encriptar(usuario.getClave()));
			user=usuarioEJBUsuario.iniciarSession(usuario);
			if(user != null){
				//Almacenar la SESSION DE JSF
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
				
				redireccion="/protegido/principal?faces-redirect=true";
			}else{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Credenicales Incorrectas"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error Incio de Session"));
		}
		return redireccion;
	}
	
	public UsuarioController getUsuarioController() {
		return usuarioController;
	}

	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

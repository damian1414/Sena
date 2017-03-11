package com.prueba.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.prueba.ejb.EmpleadoFacadeLocal;
import com.prueba.ejb.SucursalFacadeLocal;
import com.prueba.ejb.TipoUsuarioFacadeLocal;
import com.prueba.ejb.UsuarioFacadeLocal;
import com.prueba.model.Empleado;
import com.prueba.model.Sucursal;
import com.prueba.model.TipoUsuario;
import com.prueba.model.Usuario;

import static org.apache.commons.codec.binary.Base64.encodeBase64;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

@ManagedBean
@javax.faces.bean.ViewScoped
public class UsuarioController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private Usuario usuario;
	@Inject
	private Empleado empleado;
	@Inject
	private Sucursal sucursal;
	@Inject
	private TipoUsuario tipoUsuario;
	

	
	private String accion="R";

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	@EJB
	UsuarioFacadeLocal usuarioEJB;
	
	@EJB
	EmpleadoFacadeLocal empleadoEJB;
	
	@EJB
	SucursalFacadeLocal sucursalEJB;
	
	@EJB
	TipoUsuarioFacadeLocal tipoUsuarioEJB;
	
	private List<Sucursal> listaSucursal;
	
	private List<TipoUsuario> listaTipoUsuario;
	
	private List<Empleado> listaEmpleados;
	private List<Usuario> listaUsuarios;

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public List<TipoUsuario> getListaTipoUsuario() {
		return listaTipoUsuario;
	}

	public void setListaTipoUsuario(List<TipoUsuario> listaTipoUsuario) {
		this.listaTipoUsuario = listaTipoUsuario;
	}

	public List<Sucursal> getListaSucursal() {
		return listaSucursal;
	}

	public void setListaSucursal(List<Sucursal> listaSucursal) {
		this.listaSucursal = listaSucursal;
	}

	
	
	
	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	@PostConstruct
	public void init(){
		listaSucursal=sucursalEJB.findAll();
		listaTipoUsuario=tipoUsuarioEJB.findAll();
		listaUsuarios=usuarioEJB.findAll();
	}
	
	public void registrar(){
		try {
		
			empleado.setSucursal(sucursal);
			empleadoEJB.create(empleado);
			this.usuario.setEmpleado(empleado);
			this.usuario.setTipoUsuario(tipoUsuario);
			usuario.setClave(encriptar(usuario.getClave()));
			usuarioEJB.create(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se registro"));
			listaUsuarios=usuarioEJB.findAll();
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('wdialog').hide();");
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
		}
		
	}
	public void cambiarAccion(){
		
		usuario=new Usuario();
		empleado=new Empleado();
		sucursal=new Sucursal();
		tipoUsuario=new TipoUsuario();
		RequestContext.getCurrentInstance().reset("frmSecundario:uno");
		//RequestContext.getCurrentInstance().execute("PF('wdialog').show();");
		this.accion="R";
	}
	
	public void leerUsuario(Usuario usuarioSeleccion){
		usuario= new Usuario();
		RequestContext.getCurrentInstance().reset("frmEditar:editar");
		usuario=usuarioSeleccion;
		empleado=empleadoEJB.empleados(usuario.getEmpleado().getIdEmpelado());
		sucursal=empleado.getSucursal();
		tipoUsuario=usuario.getTipoUsuario();
		this.setAccion("M");
		
	}
	public void limpiar(){
		RequestContext.getCurrentInstance().reset("frmSecundario:uno");
		RequestContext.getCurrentInstance().reset("frmEditar:editar");
	}
	public void modificar(){
		try {
			empleado.setSucursal(sucursal);
			empleadoEJB.edit(empleado);
			usuario.setTipoUsuario(tipoUsuario);
			usuarioEJB.editarUsuario(usuario);
			listaUsuarios=usuarioEJB.findAll();
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('wdialogEditar').hide();");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Modificación Exitosa"));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","No se pudo ejecutar la modificación "));
		}
	}
	
	public void eliminar(Usuario usuarioEliminar){
		
		try {
			usuarioEJB.remove(usuarioEliminar);
			empleadoEJB.eliminarEmpleado(usuarioEliminar.getEmpleado().getIdEmpelado());
			listaUsuarios=usuarioEJB.findAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se elimino exitosamente"));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","No se pudo ejecutar la eliminacón "));
		}
	}
	public String encriptar(String clave) throws Exception {
		 String alg = "AES";
	    // Definición del modo de cifrado a utilizar
	     String ci = "AES/CBC/PKCS5Padding";
		 Cipher cipher = Cipher.getInstance(ci);
		 String key = "92AE31A79FEEB2A3"; 
		 String iv = "0123456789ABCDEF";
         SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
         IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
         cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
         byte[] encrypted = cipher.doFinal(clave.getBytes());
         return new String(encodeBase64(encrypted));
	}
	
	public String desencriptar(String encriptada)throws Exception{
		 String alg = "AES";
		 String ci = "AES/CBC/PKCS5Padding";
		 String key = "92AE31A79FEEB2A3"; 
		 String iv = "0123456789ABCDEF";
		 Cipher cipher = Cipher.getInstance(ci);
         SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
         IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
         byte[] enc = decodeBase64(encriptada);
         cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
         byte[] decrypted = cipher.doFinal(enc);
         return new String(decrypted);
	}

}

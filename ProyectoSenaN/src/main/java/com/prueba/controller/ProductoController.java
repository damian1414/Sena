package com.prueba.controller;

import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.prueba.ejb.EmpleadoFacadeLocal;
import com.prueba.ejb.ProductoFacadeLocal;
import com.prueba.ejb.ProveedorFacadeLocal;
import com.prueba.ejb.SucursalFacadeLocal;
import com.prueba.ejb.TipoProductoFacadeLocal;
import com.prueba.ejb.TipoUsuarioFacadeLocal;
import com.prueba.ejb.UsuarioFacadeLocal;
import com.prueba.model.Empleado;
import com.prueba.model.Producto;
import com.prueba.model.Proveedor;
import com.prueba.model.Sucursal;
import com.prueba.model.TipoProducto;
import com.prueba.model.TipoUsuario;
import com.prueba.model.Usuario;

import static org.apache.commons.codec.binary.Base64.encodeBase64;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

@ManagedBean
@ViewScoped
public class ProductoController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Sucursal sucursal;
	@Inject
	private TipoProducto tipoProducto;
	
	@Inject
	private Producto producto;
	@Inject
	private Proveedor proveedor;	
	
	private String accion="R";
	@EJB
	SucursalFacadeLocal sucursalEJB;
	@EJB
	TipoProductoFacadeLocal tipoProductoEJB;
	@EJB
	ProveedorFacadeLocal proveedorEJB;
	@EJB
	ProductoFacadeLocal productoEJB;
	
	
	private List<Sucursal> listaSucursal;
	
	private List<TipoProducto> listaTipoProducto;
	
	private List<Proveedor> listaProveedor;
	
	private List<Producto> listaProducto;
	
	
	
	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
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

	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
	@PostConstruct
	public void init(){
		listaSucursal=sucursalEJB.findAll();
		listaTipoProducto=tipoProductoEJB.findAll();
		listaProveedor=proveedorEJB.findAll();
		listaProducto=productoEJB.findAll();
	}
	
	public List<TipoProducto> getListaTipoProducto() {
		return listaTipoProducto;
	}

	public void setListaTipoProducto(List<TipoProducto> listaTipoProducto) {
		this.listaTipoProducto = listaTipoProducto;
	}
	
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
	public List<Proveedor> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaProveedor(List<Proveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public void registrar(){
		try {

			UUID codigo=UUID.randomUUID();
			String codigo1=codigo.toString();
			producto.setCodigo(codigo1);
			producto.setProveedor(proveedor);
			producto.setSucursal(sucursal);
			producto.setTipoProducto(tipoProducto);
			productoEJB.create(producto);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se registro"));
			listaProducto=productoEJB.findAll();
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('wdialog').hide();");
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
		}
		
	}
	public void cambiarAccion(){
		RequestContext.getCurrentInstance().reset("frmSecundario:uno");
		this.accion="R";
	}
	
	
	
	public void leerProducto(Producto productoSeleccion){
		RequestContext.getCurrentInstance().reset("frmEditar:editar");
		producto=productoSeleccion;
		sucursal=producto.getSucursal();
		tipoProducto=producto.getTipoProducto();
		proveedor=producto.getProveedor();
		
	}
	public void limpiar(){
		RequestContext.getCurrentInstance().reset("frmSecundario:p1");
		RequestContext.getCurrentInstance().reset("frmEditar:editar");
	}
	public void modificar(){
		try {
			producto.setSucursal(sucursal);
			producto.setTipoProducto(tipoProducto);
			producto.setProveedor(proveedor);
			productoEJB.edit(producto);
			listaProducto=productoEJB.findAll();
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('wdialogEditar').hide();");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Modificación Exitosa"));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","No se pudo ejecutar la modificación "));
		}
	}
	
	public void eliminar(Producto productoEliminar){
		
		try {
			productoEJB.remove(productoEliminar);
			listaProducto=productoEJB.findAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se elimino exitosamente"));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","No se pudo ejecutar la eliminacón "));
		}
	}

}

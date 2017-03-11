package com.prueba.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;

import com.prueba.ejb.ClienteFacadeLocal;
import com.prueba.ejb.DetalleVentaFacadeLocal;
import com.prueba.ejb.ProductoFacadeLocal;
import com.prueba.ejb.ProductoVentaFacadeLocal;
import com.prueba.ejb.VentaFacadeLocal;
import com.prueba.model.Cliente;
import com.prueba.model.DetalleVenta;
import com.prueba.model.Producto;
import com.prueba.model.ProductoVenta;
import com.prueba.model.ProductoVentaDetalle;
import com.prueba.model.Usuario;
import com.prueba.model.Venta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@SessionScoped
public class VentasController implements Serializable {



	@EJB
	ClienteFacadeLocal clienteEJB;
	
	@EJB
	ProductoFacadeLocal productoEJB;
	
	@EJB
	VentaFacadeLocal ventaEJB;
	
	@EJB
	ProductoVentaFacadeLocal productoVentaEJB;
	
	@EJB
	DetalleVentaFacadeLocal detalleVentaEJB;
	
	@Inject
	Cliente cliente;
	

	@Inject
	Producto producto;
	
	@Inject
	Venta venta;
	
	@Inject
	ProductoVenta productoVentaRealizar;
	
	@Inject
	DetalleVenta detalleVenta;

	private Long total1=(long) 0;
	
	private int estado=0;
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Integer getCantidadEditar() {
		return cantidadEditar;
	}

	public void setCantidadEditar(Integer cantidadEditar) {
		this.cantidadEditar = cantidadEditar;
	}

	private Integer cantidadEditar=0;
	
	
	private List<Cliente> listaClientes ;
	
	private List<Producto> listaProducto;
	
	private ProductoVentaDetalle productoVenta = new ProductoVentaDetalle(); 
	
	private List<Producto> listaProductoSeleccionados = new ArrayList<Producto>();
	
	private Integer cantidad;
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public List<Producto> getListaProductoSeleccionados() {
		return listaProductoSeleccionados;
	}

	public void setListaProductoSeleccionados(
			List<Producto> listaProductoSeleccionados) {
		this.listaProductoSeleccionados = listaProductoSeleccionados;
	}

	public ProductoVentaDetalle getProductoVenta() {
		return productoVenta;
	}

	public void setProductoVenta(ProductoVentaDetalle productoVenta) {
		this.productoVenta = productoVenta;
	}

	public void registrar(){
		try {
			clienteEJB.create(cliente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se registro"));
			listaClientes=clienteEJB.findAll();
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('wdialog').hide();");
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
		}
		
	}
	
	public void limpiar(){
		RequestContext.getCurrentInstance().reset("frmSecundario:p1");
		RequestContext.getCurrentInstance().reset("frmEditar:editar");
	}
	
	public void leerCliente(Cliente clienteSeleccion) throws IOException{
		//RequestContext.getCurrentInstance().reset("frmEditar:editar");
	//RequestContext.getCurrentInstance().execute("vCliente.hide()");
		estado=0;
		cliente=clienteSeleccion;
		listaProducto=productoEJB.listaProductosStock();
		listaProductoSeleccionados= new ArrayList<Producto>();
		FacesContext.getCurrentInstance().getExternalContext().redirect("seleccionarProducto.jsf");
		productoVenta=new ProductoVentaDetalle();
		total1=(long) 0;
	}
	
	public void leerProducto(Producto productoSeleccion){
		producto=productoSeleccion;
		
		
	}
	public void registrarCantidad(){
		
		if(producto.getCantidad()<cantidad){		
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","La cantidad de ventas es superiro a la que se encuentra en el stock"));
			cantidad=null;
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('wdialog').hide();");
		}else{
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('wdialog').hide();");
			cantidadEditar=producto.getCantidad();
			cantidadEditar=cantidadEditar-cantidad;
			productoEJB.modificarProducto(producto.getIdProducto(), cantidadEditar);
			listaProducto=productoEJB.listaProductosStock();
			producto.setCantidad(cantidad);
			Long valorPrenda=(long)(cantidad*producto.getPrecioCompra());
			producto.setPrecioCompra(valorPrenda);
			this.listaProductoSeleccionados.add(producto);
			total1=producto.getPrecioCompra()+total1;
			productoVenta.setTotal(total1);
			productoVenta.setListaProductos(listaProductoSeleccionados);
			cantidad=null;
			estado=1;
			RequestContext.getCurrentInstance().update("vCliente:panelDetalle");
			RequestContext.getCurrentInstance().update("totalVenta:panelTotal");
			//context.update("vCliente:panelDetalle");
			
			
		}
	}
	
	public void cancelarCantidad(){
		cantidad=null;
		
	}
	
	public void eliminarRegistro(Producto productoEliminar){
		Producto p= new Producto();
		p=productoEliminar;
		for (int i = 0; i < listaProductoSeleccionados.size(); i++) {
			if(listaProductoSeleccionados.get(i)== productoEliminar){
					productoEJB.modificarProductoEliminar(productoEliminar.getIdProducto(),productoEliminar.getCantidad());
					productoVenta.setTotal(productoVenta.getTotal()-productoEliminar.getPrecioCompra());
					total1=(total1)-(productoEliminar.getPrecioCompra());
					listaProductoSeleccionados.remove(i);
					listaProducto=productoEJB.listaProductosStock();
					if(total1 == 0){
						estado=0;
						RequestContext.getCurrentInstance().update("vCliente:panelDetalle");
						RequestContext.getCurrentInstance().update("totalVenta:panelTotal");
					}
			}
		}
	}
	
	public void instanciarCliente(){
		cliente=new Cliente();
	}
	

	@PostConstruct
	public void init(){
		listaClientes=clienteEJB.findAll();
		listaProducto=productoEJB.listaProductosStock();
	}
	
	public void registrarVenta(){
		Usuario us= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
		try {
			venta=ventaEJB.procedimientoCrearVenta(us.getEmpleado().getIdEmpelado(),cliente.getIdCliente(),us.getEmpleado().getSucursal().getIdSucursal());
			for (int i = 0; i < productoVenta.getListaProductos().size(); i++) {
					
				productoVentaRealizar=new ProductoVenta();
				productoVentaRealizar.setCantidad(productoVenta.getListaProductos().get(i).getCantidad());
				
				productoVentaRealizar.setProducto(productoVenta.getListaProductos().get(i));
				productoVentaRealizar.setVenta(venta);
				productoVentaRealizar.setEstado(1);
				productoVentaEJB.create(productoVentaRealizar);
				
				
				
			}
			 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			 Date fechaR=new Date();
			 long hora=fechaR.getTime();
			 java.sql.Timestamp horaRegistro= new java.sql.Timestamp(hora);
			 detalleVenta= new DetalleVenta(); 
			detalleVenta.setFecha(fechaR);
			detalleVenta.setHora(horaRegistro);
			detalleVenta.setPrecio(productoVenta.getTotal());
			detalleVenta.setVenta(venta);
			detalleVentaEJB.create(detalleVenta);
			productoVenta.setCliente(cliente);
			productoVenta.setVenta(venta);
			//crearReporte();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Se genero la venta satisfactoriamente"));
			crearReporte();
		
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error al generar la venta"));
		}
		
	}
	
	public void crearReporte() throws JRException{
		//File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/factura.jasper"));
		Usuario us= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		try {
			Map<String, Object> parametros = new HashMap<String , Object>();
			 //String imagen="/reporte/logo.jpg";
			
			String logo=(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/imagenes/logo.jpg"));
			parametros.put("txtlogo", this.getClass().getResource("/imagenes/paris.jpg"));
			parametros.put("txtVendedor", us.getEmpleado().getNombre());
			//Map<String, Object> map=this.basicReports(parametros);
			//parametros.put("p_logo_e",this.getClass().getResource("/reporte/logo.jpg"));
			//parametros.put("p_logo_e",this.getClass().getResourceAsStream("/reporte/logo.jpg"));
			List<ProductoVentaDetalle> listaProductoVentaDetalle= new ArrayList<ProductoVentaDetalle>();
			productoVenta.setUsuario(us);
			listaProductoVentaDetalle.add(productoVenta);
			List<Object> dato = (List) (List<ProductoVentaDetalle>) listaProductoVentaDetalle;
			JRBeanCollectionDataSource productoVentaDetalle=new JRBeanCollectionDataSource(listaProductoVentaDetalle);
			//JRBeanCollectionDataSource beanCollections= new JRBeanCollectionDataSource(listaClientes);
			//java.io.File jasper = new java.io.File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/factura1.jasper"));
			String jasper = (FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporte/factura1.jasper"));
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasper, parametros, productoVentaDetalle);
			
			
			HttpServletResponse response= (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			
			response.addHeader("Content-disposition", "attachment; filename=factura1.pdf");
//			response.setContentType("application/x-msdownload");            
//			response.setHeader("Content-disposition", "attachment; filename="+ "factura.pdf");
			
			ServletOutputStream stream=response.getOutputStream();
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			
//			stream.flush();
//			stream.close();
			FacesContext.getCurrentInstance().responseComplete();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}
	

	private Map<String, Object> basicReports(Map<String, Object> parametros) {
		Map<String, Object> map = null;
		if (parametros == null) {
			map = new HashMap<String, Object>();
		} else {
			map = parametros;
		}
		//String imagen=(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporte/logo.jpg"));
		//URL imagen2=(FacesContext.getCurrentInstance().getExternalContext().getResource("/reporte/logo.jpg"));
		//String imagen3="/reporte/logo.jpg";
		//map.put("p_logo_e",this.);
		String logo=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporte/imagenes/logo.jpg");
		map.put("txtlogo", logo);
		//map.put("logo",FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporte/imagenes/logo.jpg"));
		//map.put("dd",this.getClass().getResource("/webapp/reporte/logo.jpg"));
		
		return map;
	}
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	
	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Long getTotal1() {
		return total1;
	}

	public void setTotal1(Long total1) {
		this.total1 = total1;
	}
	
	public ProductoVenta getProductoVentaRealizar() {
		return productoVentaRealizar;
	}

	public void setProductoVentaRealizar(ProductoVenta productoVentaRealizar) {
		this.productoVentaRealizar = productoVentaRealizar;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	

	public DetalleVenta getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(DetalleVenta detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
}

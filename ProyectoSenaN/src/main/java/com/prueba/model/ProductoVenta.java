package com.prueba.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCTOS_VENTA", schema = "michiros")
public class ProductoVenta implements Serializable{
	
	@Id
	@Column(name="ID_PRODUCTOS_VENTA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTO_VENTA_SEQ")
	@SequenceGenerator(name="PRODUCTO_VENTA_SEQ" , sequenceName="SIB_SEQ_PRODUCTO_VENTA" , allocationSize=1)
	private int idProductoVenta;
	

	
	@ManyToOne
	@JoinColumn(name = "ID_VENTA" , nullable = false)
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO" , nullable = false)
	private Producto producto;
	

	@Column(name="CANTIDAD")
	private Integer cantidad;
	

	@Column(name="ESTADO")
	private Integer estado;
	

	public int getIdProductoVenta() {
		return idProductoVenta;
	}


	public void setIdProductoVenta(int idProductoVenta) {
		this.idProductoVenta = idProductoVenta;
	}


	public Venta getVenta() {
		return venta;
	}


	public void setVenta(Venta venta) {
		this.venta = venta;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Integer getEstado() {
		return estado;
	}


	public void setEstado(Integer estado) {
		this.estado = estado;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProductoVenta;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoVenta other = (ProductoVenta) obj;
		if (idProductoVenta != other.idProductoVenta)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ProductoVenta [idProductoVenta=" + idProductoVenta + "]";
	}


	
	
}

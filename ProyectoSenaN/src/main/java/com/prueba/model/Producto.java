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
@Table(name="PRODUCTO", schema = "michiros")
public class Producto implements Serializable{
	
	@Id
	@Column(name="ID_PRODUCTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTO_SEQ")
	@SequenceGenerator(name="PRODUCTO_SEQ" , sequenceName="SIB_SEQ_PRODUCTO" , allocationSize=1)
	private int idProducto;
	
	@Column(name="REFERENCIA")
	private String referencia;
	
	@Column(name="COLOR")
	private String color;
	
	@Column(name="TALLA")
	private String talla;
	
	@Column(name="PRECIO_COMPRA")
	private Long precioCompra;
	
	@Column(name="PRECIO_VENTA")
	private Long precioVenta;
	
	
	@Column(name="MARCA")
	private String marca;
	
	
	@Column(name="CODIGO")
	private String codigo;
	
	
	@Column(name="CANTIDAD")
	private Integer cantidad;
	
	
	
	@ManyToOne
	@JoinColumn(name = "ID_SUCURSAL" , nullable = false)
	private Sucursal sucursal;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PRODUCTO" , nullable = false)
	private TipoProducto tipoProducto;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROVEEDOR" , nullable = false)
	private Proveedor proveedor;

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Long precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Long getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Long precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	
	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProducto;
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
		Producto other = (Producto) obj;
		if (idProducto != other.idProducto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + "]";
	}
	
	
	

	}

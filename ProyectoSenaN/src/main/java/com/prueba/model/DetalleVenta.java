package com.prueba.model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
@Table(name="DETALLE_VENTA", schema = "michiros")
public class DetalleVenta implements Serializable{
	
	@Id
	@Column(name="ID_DETALLE_VENTA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETALLE_VENTA_SEQ")
	@SequenceGenerator(name="DETALLE_VENTA_SEQ" , sequenceName="SIB_SEQ_DETALLE_VENTA" , allocationSize=1)
	private int idDetalleVenta;
	
	@Column(name="PRECIO")
	private long precio;
	
	@Column(name="FECHA")
	private Date fecha;
	
	@Column(name="FECHA_HORA")
	private Timestamp hora;

	@ManyToOne
	@JoinColumn(name = "ID_VENTA" , nullable = false)
	private Venta venta;

	public int getIdDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdDetalleVenta(int idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}

	public long getPrecio() {
		return precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Timestamp getHora() {
		return hora;
	}

	public void setHora(Timestamp hora) {
		this.hora = hora;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	@Override
	public String toString() {
		return "DetalleVenta [idDetalleVenta=" + idDetalleVenta + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDetalleVenta;
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
		DetalleVenta other = (DetalleVenta) obj;
		if (idDetalleVenta != other.idDetalleVenta)
			return false;
		return true;
	}
	

	

	
	
}

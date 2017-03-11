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
@Table(name="NOVEDAD", schema = "michiros")
public class Novedad implements Serializable{
	
	@Id
	@Column(name="ID_NOVEDAD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOVEDAD_SEQ")
	@SequenceGenerator(name="NOVEDAD_SEQ" , sequenceName="SIB_SEQ_NOVEDAD" , allocationSize=1)
	private int idNovedad;
	

	@ManyToOne
	@JoinColumn(name = "ID_EMPLEADO" , nullable = false)
	private Empleado empleado;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_VENTA" , nullable = false)
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO" , nullable = false)
	private Producto producto;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_NOVEDAD" , nullable = false)
	private TipoNovedad tipoNovedad;
	


	public int getIdNovedad() {
		return idNovedad;
	}


	public void setIdNovedad(int idNovedad) {
		this.idNovedad = idNovedad;
	}


	public Empleado getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
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


	public TipoNovedad getTipoNovedad() {
		return tipoNovedad;
	}


	public void setTipoNovedad(TipoNovedad tipoNovedad) {
		this.tipoNovedad = tipoNovedad;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNovedad;
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
		Novedad other = (Novedad) obj;
		if (idNovedad != other.idNovedad)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Novedad [idNovedad=" + idNovedad + "]";
	}

	

	
	
}

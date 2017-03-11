package com.prueba.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_PRODUCTO", schema = "michiros")
public class TipoProducto implements Serializable{
	
	@Id
	@Column(name="ID_TIPO_PRODUCTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_PRODUCTO_SEQ")
	@SequenceGenerator(name="TIPO_PRODUCTO_SEQ" , sequenceName="SIB_SEQ_TIPO_PRODUCTO" , allocationSize=1)
	private int idTipoProducto;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Override
	public String toString() {
		return "TipoProducto [idTipoProducto=" + idTipoProducto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoProducto;
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
		TipoProducto other = (TipoProducto) obj;
		if (idTipoProducto != other.idTipoProducto)
			return false;
		return true;
	}

	public int getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(int idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	
	
	
}

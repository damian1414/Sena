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
@Table(name="TIPO_NOVEDAD", schema = "michiros")
public class TipoNovedad implements Serializable{
	
	@Id
	@Column(name="ID_TIPO_NOVEDAD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_NOVEDAD_SEQ")
	@SequenceGenerator(name="TIPO_NOVEDAD_SEQ",sequenceName="SIB_SEQ_TIPO_NOVEDAD" , allocationSize=1)
	private int idTipoNovedad;
	

	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	public int getIdTipoNovedad() {
		return idTipoNovedad;
	}

	public void setIdTipoNovedad(int idTipoNovedad) {
		this.idTipoNovedad = idTipoNovedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoNovedad other = (TipoNovedad) obj;
		if (idTipoNovedad != other.idTipoNovedad)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoNovedad [idTipoNovedad=" + idTipoNovedad + "]";
	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoNovedad;
		return result;
	}

	

	
	
}

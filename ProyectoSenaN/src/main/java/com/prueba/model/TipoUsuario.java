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
@Table(name="TIPO_USUARIO", schema = "michiros")
public class TipoUsuario implements Serializable{
	
	@Id
	@Column(name="ID_TIPO_USUARIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_USUARIO_SEQ")
	@SequenceGenerator(name="TIPO_USUARIO_SEQ" , sequenceName="SIB_SEQ_TIPO_USUARIO" , allocationSize=1)
	private int idTipoUsuario;
	
	@Column(name="DESCRIPCION")
	private String descripcion;

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoUsuario;
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
		TipoUsuario other = (TipoUsuario) obj;
		if (idTipoUsuario != other.idTipoUsuario)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoUsuario [idTipoUsuario=" + idTipoUsuario + "]";
	}
	
	
	
}

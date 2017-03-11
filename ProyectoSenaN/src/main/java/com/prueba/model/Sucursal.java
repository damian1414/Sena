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
@Table(name="SUCURSAL", schema = "michiros")
public class Sucursal implements Serializable{
	
	@Id
	@Column(name="ID_SUCURSAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUCURSAL_SEQ")
	@SequenceGenerator(name="SUCURSAL_SEQ" , sequenceName="SIB_SEQ_SUCURSAL" , allocationSize=1)
	private int idSucursal;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	@Column(name="DIRECCION")
	private String direccion;

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSucursal;
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
		Sucursal other = (Sucursal) obj;
		if (idSucursal != other.idSucursal)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sucursal [idSucursal=" + idSucursal + "]";
	}
	

	
	
}

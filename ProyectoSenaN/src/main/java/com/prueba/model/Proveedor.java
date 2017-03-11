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
@Table(name="PROVEEDOR", schema = "michiros")
public class Proveedor implements Serializable{
	
	@Id
	@Column(name="ID_PROVEEDOR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_PROVEEDRO_SEQ")
	@SequenceGenerator(name="TIPO_PROVEEDRO_SEQ" , sequenceName="SIB_SEQ_PROVEEDOR" , allocationSize=1)
	private int idProveedor;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	@Column(name="DIRECCION")
	private String direccion;

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
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
		result = prime * result + idProveedor;
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
		Proveedor other = (Proveedor) obj;
		if (idProveedor != other.idProveedor)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proveedor [idProveedor=" + idProveedor + "]";
	}

	

	
	
	
	
	
}

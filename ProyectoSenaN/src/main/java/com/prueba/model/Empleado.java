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
@Table(name="EMPLEADO", schema="michiros")
public class Empleado implements Serializable{
	
	@Id
	@Column(name="ID_EMPLEADO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPLEADO_SEQ")
	@SequenceGenerator(name="EMPLEADO_SEQ" , sequenceName="SIB_SEQ_EMPLEADO" , allocationSize=1)
	private int idEmpelado;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_SUCURSAL" , nullable = false)
	private Sucursal sucursal;
	
	
	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="APELLIDO")
	private String apellido;
	
	@Column(name="DOCUMENTO")
	private String documento;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name="CORREO")
	private String correo;

	public int getIdEmpelado() {
		return idEmpelado;
	}

	public void setIdEmpelado(int idEmpelado) {
		this.idEmpelado = idEmpelado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEmpelado;
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
		Empleado other = (Empleado) obj;
		if (idEmpelado != other.idEmpelado)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Persona [idEmpelado=" + idEmpelado + "]";
	}
	
	
	
}

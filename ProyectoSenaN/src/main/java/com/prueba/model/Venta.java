package com.prueba.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedStoredProcedureQuery(
		name = "crearVentas", 
		resultClasses = Venta.class, 
		procedureName = "SIB_PQ_SENA.CREAR_VENTAS", 
		parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name="P_ID_EMPLEADO", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name="P_ID_CLIENTE", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name="P_ID_SUCURSAL", type = Integer.class),
		@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, name="CURSORRETURN", type=void.class)
		}
	)
@Entity
@Table(name="VENTA", schema = "michiros")
public class Venta implements Serializable{
	
	@Id
	@Column(name="ID_VENTA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VENTA_SEQ")
	@SequenceGenerator(name="VENTA_SEQ" , sequenceName="SIB_SEQ_VENTA" , allocationSize=1)
	private int idVenta;
	
	
	
	@ManyToOne
	@JoinColumn(name="ID_EMPLEADO", nullable =false)
	private Empleado empleado;
	
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE", nullable =false)
	private Cliente cliente;
	
	

	@ManyToOne
	@JoinColumn(name = "ID_SUCURSAL" , nullable = false)
	private Sucursal sucursal;

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idVenta;
		return result;
	}


	public int getIdVenta() {
		return idVenta;
	}


	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}


	public Empleado getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Sucursal getSucursal() {
		return sucursal;
	}


	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		if (idVenta != other.idVenta)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + "]";
	}

	
	
	
}

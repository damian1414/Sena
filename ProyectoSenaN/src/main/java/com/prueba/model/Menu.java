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
@Table(name="MENU", schema="michiros")
public class Menu implements Serializable {
	
	@Id
	@Column(name="CODIGO_MENU")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MENU_SEQ")
	@SequenceGenerator(name="MENU_SEQ" , sequenceName="SIB_SEQ_MENU" , allocationSize=1)
	private int codigoMenu;
	
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="TIPO")
	private String tipo;
	
	@Column(name="TIPO_USUARIO")
	private int tipoUsuario;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_SUB_MENU")
	private Menu codigoSubMenu;
	
	@Column(name="ESTADO")
	private int estado;
	
	@Column(name="URL")
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCodigoMenu() {
		return codigoMenu;
	}

	public void setCodigoMenu(int codigoMenu) {
		this.codigoMenu = codigoMenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Menu getCodigoSubMenu() {
		return codigoSubMenu;
	}

	public void setCodigoSubMenu(Menu codigoSubMenu) {
		this.codigoSubMenu = codigoSubMenu;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoMenu;
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
		Menu other = (Menu) obj;
		if (codigoMenu != other.codigoMenu)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Menu [codigoMenu=" + codigoMenu + "]";
	}


}

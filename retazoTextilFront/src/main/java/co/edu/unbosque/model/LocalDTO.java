package co.edu.unbosque.model;

import java.io.Serializable;

public class LocalDTO implements Serializable{

	private Integer numeroLocal;

	private String direccion;

	private Long telefono;

	public LocalDTO() {
	}

	public LocalDTO(String direccion, Long telefono) {
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Integer getNumeroLocal() {
		return numeroLocal;
	}

	public void setNumeroLocal(Integer numeroLocal) {
		this.numeroLocal = numeroLocal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

}

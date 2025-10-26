package co.edu.unbosque.retazoTextil.dto;

public class LocalDTO {

	private Integer numeroLocal;

	private String direccion;

	private Integer telefono;

	public LocalDTO() {
	}

	public LocalDTO(String direccion, Integer telefono) {
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

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

}

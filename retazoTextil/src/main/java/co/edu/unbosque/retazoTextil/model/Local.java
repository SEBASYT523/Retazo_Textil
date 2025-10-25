package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "local")
public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numero_local")
	private Long numeroLocal;

	@Column(name = "direccion", nullable = false, length = 100)
	private String direccion;

	@Column(name = "telefono", nullable = false)
	private Long telefono;

	public Local() {
	}

	public Local(String direccion, Long telefono) {
		super();
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Long getNumeroLocal() {
		return numeroLocal;
	}

	public void setNumeroLocal(Long numeroLocal) {
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

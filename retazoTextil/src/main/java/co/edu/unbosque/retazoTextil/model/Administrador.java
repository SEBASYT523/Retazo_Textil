package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "administrador")
public class Administrador {

	@Id
	@Column(name = "id_empleado")
	private Integer idEmpleado;

	@Column(name = "numero_cubiculo", nullable = false, unique = true)
	private Integer numeroCubiculo;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "id_empleado")
	private Empleado empleado;

	@OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Proveedor> proveedores;

	@OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Contactar> contactos;

	public Administrador() {
	}

	public Administrador(Integer numeroCubiculo, Empleado empleado, List<Proveedor> proveedores,
			List<Contactar> contactos) {
		super();
		this.numeroCubiculo = numeroCubiculo;
		this.empleado = empleado;
		this.proveedores = proveedores;
		this.contactos = contactos;
	}

	public Administrador(Integer numeroCubiculo, Empleado empleado, List<Proveedor> pro) {
		this.numeroCubiculo = numeroCubiculo;

		this.empleado = empleado;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getNumeroCubiculo() {
		return numeroCubiculo;
	}

	public void setNumeroCubiculo(Integer numeroCubiculo) {
		this.numeroCubiculo = numeroCubiculo;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public List<Contactar> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contactar> contactos) {
		this.contactos = contactos;
	}

}

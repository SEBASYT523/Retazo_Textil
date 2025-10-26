package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contactar")
public class Contactar {

	@EmbeddedId
	private ContactarId id;

	@Column(name = "fecha_solicitud")
	private LocalDate fechaSolicitud;

	@Column(name = "fecha_entrega", nullable = false)
	private LocalDate fechaEntrega;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("proveedorIdProveedor")
	@JoinColumn(name = "proveedor_id_proveedor", nullable = false)
	private Proveedor proveedor;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("administradorIdEmpleado")
	@JoinColumn(name = "administrador_id_empleado", nullable = false)
	private Administrador administrador;

	public Contactar() {
	}

	public Contactar(ContactarId id, LocalDate fechaSolicitud, LocalDate fechaEntrega, Proveedor proveedor,
			Administrador administrador) {
		this.id = id;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
		this.proveedor = proveedor;
		this.administrador = administrador;
	}

	public ContactarId getId() {
		return id;
	}

	public void setId(ContactarId id) {
		this.id = id;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

}

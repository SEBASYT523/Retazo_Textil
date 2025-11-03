package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;

public class ContactarDTO implements Serializable{

	private LocalDate fechaSolicitud;

	private LocalDate fechaEntrega;

	private Integer proveedorId;

	private Integer administradorId;

	public ContactarDTO() {
	}

	public ContactarDTO(LocalDate fechaSolicitud, LocalDate fechaEntrega, Integer proveedorId,
			Integer administradorId) {
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
		this.proveedorId = proveedorId;
		this.administradorId = administradorId;
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

	public Integer getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(Integer proveedorId) {
		this.proveedorId = proveedorId;
	}

	public Integer getAdministradorId() {
		return administradorId;
	}

	public void setAdministradorId(Integer administradorId) {
		this.administradorId = administradorId;
	}

}

package co.edu.unbosque.retazoTextil.dto;

import java.time.LocalDate;
import java.util.List;

public class AdministradorDTO {

	private Integer idEmpleado;

	private Integer numeroCubiculo;

	private LocalDate fechaSolicitud;

	private LocalDate fechaEntrega;

	private List<Integer> proveedoresId;

	private List<Integer> contactosId;

	public AdministradorDTO() {
	}

	public AdministradorDTO(Integer numeroCubiculo, LocalDate fechaSolicitud, LocalDate fechaEntrega) {
		this.numeroCubiculo = numeroCubiculo;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
	}

	public AdministradorDTO(Integer numeroCubiculo, LocalDate fechaSolicitud, LocalDate fechaEntrega,
			List<Integer> proveedoresId, List<Integer> contactosId) {
		super();
		this.numeroCubiculo = numeroCubiculo;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
		this.proveedoresId = proveedoresId;
		this.contactosId = contactosId;
	}

	public Integer getNumeroCubiculo() {
		return numeroCubiculo;
	}

	public void setNumeroCubiculo(Integer numeroCubiculo) {
		this.numeroCubiculo = numeroCubiculo;
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

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public List<Integer> getProveedoresId() {
		return proveedoresId;
	}

	public void setProveedoresId(List<Integer> proveedoresId) {
		this.proveedoresId = proveedoresId;
	}

	public List<Integer> getContactosId() {
		return contactosId;
	}

	public void setContactosId(List<Integer> contactosId) {
		this.contactosId = contactosId;
	}

}

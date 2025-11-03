package co.edu.unbosque.model;

import java.io.Serializable;
import java.time.LocalDate;

public class BodegaDTO implements Serializable{

	private Integer numeroBodega;

	private LocalDate fechaCompra;

	private Integer capacidad;

	private Integer localId;

	public BodegaDTO() {

	}

	public BodegaDTO(LocalDate fechaCompra, Integer capacidad, Integer localId) {
		super();
		this.fechaCompra = fechaCompra;
		this.capacidad = capacidad;
		this.localId = localId;
	}

	public Integer getNumeroBodega() {
		return numeroBodega;
	}

	public void setNumeroBodega(Integer numeroBodega) {
		this.numeroBodega = numeroBodega;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Integer getLocalId() {
		return localId;
	}

	public void setLocalId(Integer localId) {
		this.localId = localId;
	}

}

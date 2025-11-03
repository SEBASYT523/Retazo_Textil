package co.edu.unbosque.model;

import java.io.Serializable;

public class GuardarDTO implements Serializable{

	private Integer productoId;

	private Integer bodegaId;

	public GuardarDTO() {
	}

	public GuardarDTO(Integer productoId, Integer bodegaId) {
		this.productoId = productoId;
		this.bodegaId = bodegaId;
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public Integer getBodegaId() {
		return bodegaId;
	}

	public void setBodegaId(Integer bodegaId) {
		this.bodegaId = bodegaId;
	}

}

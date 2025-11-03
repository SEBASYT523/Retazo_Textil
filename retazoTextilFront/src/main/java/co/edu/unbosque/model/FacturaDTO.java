package co.edu.unbosque.model;

import java.io.Serializable;

public class FacturaDTO implements Serializable{

	private Integer numeroFactura;

	private String metodoPago;

	private Integer ventaId;

	public FacturaDTO() {
	}

	public FacturaDTO(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public FacturaDTO(String metodoPago, Integer ventaId) {
		super();
		this.metodoPago = metodoPago;
		this.ventaId = ventaId;
	}

	public Integer getVentaId() {
		return ventaId;
	}

	public void setVentaId(Integer ventaId) {
		this.ventaId = ventaId;
	}

	public Integer getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Integer numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Integer getInteger() {
		return ventaId;
	}

	public void setInteger(Integer ventaId) {
		this.ventaId = ventaId;
	}

}

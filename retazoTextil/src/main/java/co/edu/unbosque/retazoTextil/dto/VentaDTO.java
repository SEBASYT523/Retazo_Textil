package co.edu.unbosque.retazoTextil.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VentaDTO {

	private Integer codigoVenta;

	private BigDecimal valorTotal;

	private LocalDate fechaVenta;

	private Integer empleadoId;

	private Integer facturaId;

	public VentaDTO() {
	}

	public VentaDTO(BigDecimal valorTotal, LocalDate fechaVenta, Integer empleado, Integer factura) {
		this.valorTotal = valorTotal;
		this.fechaVenta = fechaVenta;
		this.empleadoId = empleado;
		this.facturaId = factura;
	}

	public Integer getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(Integer codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Integer getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Integer empleadoId) {
		this.empleadoId = empleadoId;
	}

	public Integer getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(Integer facturaId) {
		this.facturaId = facturaId;
	}

}

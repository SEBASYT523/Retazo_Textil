package co.edu.unbosque.retazoTextil.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoDTO {

	private Integer clienteId;

	private Integer productoId;

	private LocalDate fechaPedidoDTO;

	private Integer cantidad;

	private BigDecimal total;

	public PedidoDTO() {
	}

	public PedidoDTO(Integer productoId, LocalDate fechaPedidoDTO, Integer cantidad, BigDecimal total) {
		super();
		this.productoId = productoId;
		this.fechaPedidoDTO = fechaPedidoDTO;
		this.cantidad = cantidad;
		this.total = total;
	}
	
	

	public PedidoDTO(Integer clienteId, Integer productoId, LocalDate fechaPedidoDTO, Integer cantidad,
			BigDecimal total) {
		super();
		this.clienteId = clienteId;
		this.productoId = productoId;
		this.fechaPedidoDTO = fechaPedidoDTO;
		this.cantidad = cantidad;
		this.total = total;
	}

	public LocalDate getFechaPedidoDTO() {
		return fechaPedidoDTO;
	}

	public void setFechaPedidoDTO(LocalDate fechaPedidoDTO) {
		this.fechaPedidoDTO = fechaPedidoDTO;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

}

package co.edu.unbosque.model;

import java.math.BigDecimal;

public class PedidoDTO {

	private Integer clienteId;

	private Integer productoId;

	private String fechaPedidoDTO;

	private Integer cantidad;

	private BigDecimal total;

	public PedidoDTO() {
	}

	public String getFechaPedidoDTO() {
		return fechaPedidoDTO;
	}

	public void setFechaPedidoDTO(String fechaPedidoDTO) {
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

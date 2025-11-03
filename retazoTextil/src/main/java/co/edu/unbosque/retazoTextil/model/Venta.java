package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "venta")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_venta")
	private Integer codigoVenta;

	@Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorTotal;

	@Column(name = "fecha_venta", nullable = false)
	private LocalDate fechaVenta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empleado_id_empleado", nullable = false)
	private Empleado empleado;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "factura_numero_factura", nullable = true)
	private Factura factura;

	public Venta() {
	}

	public Venta(BigDecimal valorTotal, LocalDate fechaVenta, Empleado empleado, Factura factura) {
		this.valorTotal = valorTotal;
		this.fechaVenta = fechaVenta;
		this.empleado = empleado;
		this.factura = factura;
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

}

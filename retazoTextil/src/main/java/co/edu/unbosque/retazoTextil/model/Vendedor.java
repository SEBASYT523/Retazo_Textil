package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendedor")
public class Vendedor {

	@Id
	@Column(name = "id_empleado")
	private Integer idEmpleado;

	@Column(name = "numero_productos_vendidos", nullable = false, unique = true)
	private Integer numeroProductosVendidos;

	@Column(name = "total_ventas")
	private Integer totalVentas;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_numero_local", nullable = false)
	private Local local;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "id_empleado")
	private Empleado empleado;

	public Vendedor() {

	}

	public Vendedor(Integer numeroProductosVendidos, Integer totalVentas, Local local, Empleado empleado) {
		this.numeroProductosVendidos = numeroProductosVendidos;
		this.totalVentas = totalVentas;
		this.local = local;
		this.empleado = empleado;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getNumeroProductosVendidos() {
		return numeroProductosVendidos;
	}

	public void setNumeroProductosVendidos(Integer numeroProductosVendidos) {
		this.numeroProductosVendidos = numeroProductosVendidos;
	}

	public Integer getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(Integer totalVentas) {
		this.totalVentas = totalVentas;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	
}

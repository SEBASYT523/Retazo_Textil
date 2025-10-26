package co.edu.unbosque.retazoTextil.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "bodega")

public class Bodega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numero_bodega")
	private Integer numeroBodega;

	@Column(name = "fecha_compra", nullable = false)
	private LocalDate fechaCompra;

	@Column(name = "capacidad", nullable = false)
	private Integer capacidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_numero_local", nullable = false, foreignKey = @ForeignKey(name = "fk_bodega_local"))
	private Local local;

	public Bodega() {

	}

	public Bodega(LocalDate fechaCompra, Integer capacidad, Local local) {
		super();
		this.fechaCompra = fechaCompra;
		this.capacidad = capacidad;
		this.local = local;
	}

	public Integer getNumeroBodega() {
		return numeroBodega;
	}

	public void setNumeroBodega(Integer numeroBodega) {
		this.numeroBodega = numeroBodega;
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

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

}

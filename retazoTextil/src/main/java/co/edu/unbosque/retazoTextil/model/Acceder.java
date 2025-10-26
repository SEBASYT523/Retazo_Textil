package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "acceder")
public class Acceder {

	@EmbeddedId
	private AccederId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("bodegaNumeroBodega")
	@JoinColumn(name = "bodega_numero_bodega", nullable = false)
	private Bodega bodega;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("vendedorIdEmpleado")
	@JoinColumn(name = "vendedor_id_empleado", nullable = false)
	private Vendedor vendedor;

	public Acceder() {
	}

	public Acceder(AccederId id, Bodega bodega, Vendedor vendedor) {
		this.id = id;
		this.bodega = bodega;
		this.vendedor = vendedor;
	}

	public AccederId getId() {
		return id;
	}

	public void setId(AccederId id) {
		this.id = id;
	}

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

}

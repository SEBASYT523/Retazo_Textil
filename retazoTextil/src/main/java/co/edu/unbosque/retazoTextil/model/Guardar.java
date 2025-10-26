package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "guardar")
public class Guardar {

	@EmbeddedId
	private GuardarId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("productoCodProducto")
	@JoinColumn(name = "producto_cod_producto", nullable = false)
	private Producto producto;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("bodegaNumeroBodega")
	@JoinColumn(name = "bodega_numero_bodega", nullable = false)
	private Bodega bodega;

	public Guardar() {
	}

	public Guardar(GuardarId id, Producto producto, Bodega bodega) {
		this.id = id;
		this.producto = producto;
		this.bodega = bodega;
	}

	public GuardarId getId() {
		return id;
	}

	public void setId(GuardarId id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}
	
	
}

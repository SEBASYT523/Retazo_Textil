package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_factura")
    private Integer numeroFactura;

    @Column(name = "metodo_pago", nullable = false, length = 30)
    private String metodoPago;


    @OneToOne(mappedBy = "factura", fetch = FetchType.LAZY)
    private Venta venta;

    public Factura() {}

    public Factura(String metodoPago) {
        this.metodoPago = metodoPago;
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

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
    
    
}

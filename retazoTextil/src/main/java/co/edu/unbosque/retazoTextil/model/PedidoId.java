package co.edu.unbosque.retazoTextil.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PedidoId implements Serializable {

   
	private static final long serialVersionUID = 1L;

	@Column(name = "cliente_id_cliente")
    private Integer clienteId;

    @Column(name = "producto_cod_producto")
    private Integer productoId;

    public PedidoId() {}

    public PedidoId(Integer clienteId, Integer productoId) {
        this.clienteId = clienteId;
        this.productoId = productoId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoId)) return false;
        PedidoId that = (PedidoId) o;
        return Objects.equals(clienteId, that.clienteId) &&
               Objects.equals(productoId, that.productoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, productoId);
    }
}

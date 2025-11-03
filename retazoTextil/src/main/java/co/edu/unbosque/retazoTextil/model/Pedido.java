package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pedido")
public class Pedido {

    @EmbeddedId
    private PedidoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clienteId")
    @JoinColumn(name = "cliente_id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productoId")
    @JoinColumn(name = "producto_cod_producto", nullable = false)
    private Producto producto;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    public Pedido() {}

    public Pedido(PedidoId pedidoId,Cliente cliente, Producto producto, LocalDate fechaPedido, Integer cantidad, BigDecimal total) {
        this.id = pedidoId;
        this.cliente = cliente;
        this.producto = producto;
        this.fechaPedido = fechaPedido;
        this.cantidad = cantidad;
        this.total = total;
    }

    public PedidoId getId() {
        return id;
    }

    public void setId(PedidoId id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
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
}

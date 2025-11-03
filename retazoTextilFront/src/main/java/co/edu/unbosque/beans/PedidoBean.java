package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import co.edu.unbosque.model.PedidoDTO;
import co.edu.unbosque.model.persistence.ExternalHTTPRequestHandler;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("PedidoBean")
@SessionScoped
public class PedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PedidoDTO> pedidos = new ArrayList<>();
    private PedidoDTO pedidoSeleccionado;

    @Inject
    private ClienteBean cliente;

    private static final String BASE_URL = "http://localhost:8080/api/pedidos/cliente/pedido/"
    		+ "";

    @PostConstruct
    public void init() {
    }

    public String cargarPedidos() {
        try {
            if (cliente == null || cliente.getClienteActual() == null) {
                System.out.println("⚠️ ClienteBean o clienteActual es null");
                pedidos = new ArrayList<>();
                return "";
            }

            Integer idCliente = cliente.getClienteActual().getIdCliente();
            System.out.println("📥 Cargando pedidos del cliente ID: " + idCliente);
         
            pedidos = ExternalHTTPRequestHandler.doGetAndConvertToDTOListPedido(BASE_URL + idCliente);

            if (pedidos == null || pedidos.isEmpty()) {
                System.out.println("❌ No se encontraron pedidos para este cliente");
            } else {
                System.out.println("✅ Pedidos cargados correctamente:");
                pedidos.forEach(p -> System.out.println(
                        "→ Fecha: " + p.getFechaPedidoDTO() +
                        ", Cantidad: " + p.getCantidad() +
                        ", Total: " + p.getTotal()));
            }

            return "pedidosCliente?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            pedidos = new ArrayList<>();
            return "";
        }
    }

    public void verDetalles(PedidoDTO pedido) {
        this.pedidoSeleccionado = pedido;
    }

    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public PedidoDTO getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(PedidoDTO pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }
}



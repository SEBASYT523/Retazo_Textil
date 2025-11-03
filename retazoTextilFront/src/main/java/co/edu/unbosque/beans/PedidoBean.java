package co.edu.unbosque.beans;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.unbosque.model.PedidoDTO;
import co.edu.unbosque.model.persistence.ExternalHTTPRequestHandler;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("PedidoBean")
@SessionScoped
public class PedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PedidoDTO> pedidos;
    private PedidoDTO pedidoSeleccionado;
    private ClienteBean cliente;

    private static final String BASE_URL = "http://localhost:8080/cliente/pedido/";

    @PostConstruct
    public void init() {
        pedidos = new ArrayList<>();
        cargarPedidos();
    }

    public void cargarPedidos() {
        try {
            Integer idCliente = cliente.getClienteActual().getIdCliente(); 
            System.out.println(idCliente);

			if (idCliente == null) return;
           
            String response = ExternalHTTPRequestHandler.doGet(BASE_URL + idCliente);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<PedidoDTO>>() {}.getType();
            pedidos = gson.fromJson(response, listType);
        } catch (Exception e) {
            e.printStackTrace();
            pedidos = new ArrayList<>();
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

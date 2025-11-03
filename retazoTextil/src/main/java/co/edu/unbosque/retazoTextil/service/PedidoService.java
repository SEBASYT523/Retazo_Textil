package co.edu.unbosque.retazoTextil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.retazoTextil.dto.PedidoDTO;
import co.edu.unbosque.retazoTextil.model.*;
import co.edu.unbosque.retazoTextil.repository.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ProductoRepository productoRepo;

    
    public Pedido crearPedido(PedidoDTO dto) {
        Optional<Cliente> clienteOpt = clienteRepo.findById(dto.getClienteId());
        Optional<Producto> productoOpt = productoRepo.findById(dto.getProductoId());

        if (clienteOpt.isEmpty() || productoOpt.isEmpty()) {
            return null;
        }

        Pedido pedido = new Pedido(
                clienteOpt.get(),
                productoOpt.get(),
                dto.getFechaPedidoDTO(),
                dto.getCantidad(),
                dto.getTotal()
        );

        return pedidoRepo.save(pedido);
    }

    
    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

    
    public Pedido obtenerPedidoPorId(Integer clienteId, Integer productoId) {
        PedidoId id = new PedidoId(clienteId, productoId);
        return pedidoRepo.findById(id).orElse(null);
    }

    
    public int eliminarPedido(Integer clienteId, Integer productoId) {
        PedidoId id = new PedidoId(clienteId, productoId);
        if (!pedidoRepo.existsById(id)) {
            return 1;
        }
        pedidoRepo.deleteById(id);
        return 0;
    }
}

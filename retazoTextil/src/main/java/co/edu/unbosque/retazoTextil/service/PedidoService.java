package co.edu.unbosque.retazoTextil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Pedido pedido = new Pedido(new PedidoId(dto.getClienteId(), dto.getProductoId()),
                clienteOpt.get(),
                productoOpt.get(),
                dto.getFechaPedidoDTO(),
                dto.getCantidad(),
                dto.getTotal()
        );

        return pedidoRepo.save(pedido);
    }
    
    public List<PedidoDTO> obtenerPedidosPorCliente(Integer idCliente) {
        return pedidoRepo.findByCliente_IdCliente(idCliente)
                .stream()
                .map(p -> {
                    PedidoDTO dto = new PedidoDTO();
                    dto.setClienteId(p.getCliente().getIdCliente());
                    dto.setProductoId(p.getProducto().getCodProducto());
                    dto.setFechaPedidoDTO(p.getFechaPedido());
                    dto.setCantidad(p.getCantidad());
                    dto.setTotal(p.getTotal());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    
    public List<PedidoDTO> listarPedidos() {
    	List<Pedido> entidades = pedidoRepo.findAll();
		List<PedidoDTO> dtos = new ArrayList<>();

		for (Pedido a : entidades) {
			Integer numeroCliente = a.getCliente().getIdCliente();
			Integer idProducto = a.getProducto().getCodProducto();

			PedidoDTO dto = new PedidoDTO(numeroCliente,idProducto, a.getFechaPedido(), a.getCantidad(), a.getTotal());
			dtos.add(dto);
		}

		return dtos;
    }

    
    public PedidoDTO obtenerPedidoPorId(Integer clienteId, Integer productoId) {
        PedidoId id = new PedidoId(clienteId, productoId);
       Pedido respuesta = pedidoRepo.findById(id).orElse(null);
        if(respuesta == null) {
        	return null;
        }
        return new PedidoDTO(clienteId, productoId, respuesta.getFechaPedido(), respuesta.getCantidad(),respuesta.getTotal());
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

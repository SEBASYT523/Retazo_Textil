package co.edu.unbosque.retazoTextil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.retazoTextil.dto.PedidoDTO;
import co.edu.unbosque.retazoTextil.model.Pedido;
import co.edu.unbosque.retazoTextil.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping
	public ResponseEntity<String> crearPedido(@RequestBody PedidoDTO dto) {
		Pedido nuevo = pedidoService.crearPedido(dto);
		if (nuevo == null) {
			return ResponseEntity.badRequest().body("❌ No se pudo crear el pedido. Cliente o producto no existen.");
		}
		return ResponseEntity.ok("✅ Pedido creado correctamente.");
	}

	@GetMapping
	public ResponseEntity<List<Pedido>> listarPedidos() {
		return ResponseEntity.ok(pedidoService.listarPedidos());
	}

	@GetMapping("/{clienteId}/{productoId}")
	public ResponseEntity<?> obtenerPedido(@PathVariable Integer clienteId, @PathVariable Integer productoId) {
		Pedido pedido = pedidoService.obtenerPedidoPorId(clienteId, productoId);
		if (pedido == null) {
			return ResponseEntity.badRequest().body("❌ Pedido no encontrado.");
		}
		return ResponseEntity.ok(pedido);
	}

	@DeleteMapping("/{clienteId}/{productoId}")
	public ResponseEntity<String> eliminarPedido(@PathVariable Integer clienteId, @PathVariable Integer productoId) {
		int result = pedidoService.eliminarPedido(clienteId, productoId);
		if (result == 1) {
			return ResponseEntity.badRequest().body("❌ No existe el pedido especificado.");
		}
		return ResponseEntity.ok("✅ Pedido eliminado correctamente.");
	}
	
	@GetMapping("/cliente/{idCliente}")
    public List<PedidoDTO> obtenerPedidosPorCliente(@PathVariable Integer idCliente) {
        return pedidoService.obtenerPedidosPorCliente(idCliente);
    }
}

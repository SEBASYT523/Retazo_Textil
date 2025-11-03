package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.VentaDTO;
import co.edu.unbosque.retazoTextil.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta")
@CrossOrigin(origins = "*")
public class VentaController {

	@Autowired
	private VentaService ventaService;

	@PostMapping("/crear")
	public ResponseEntity<String> crearVenta(@RequestBody VentaDTO data) {
		int result = ventaService.create(data);

		switch (result) {
		case 0:
			return ResponseEntity.ok("✅ Venta creada correctamente.");
		case 1:
			return ResponseEntity.badRequest().body("❌ Error: Empleado no encontrado.");
		case 2:
			return ResponseEntity.badRequest().body("❌ Error: Factura no encontrada.");
		default:
			return ResponseEntity.internalServerError().body("⚠️ Error desconocido al crear la venta.");
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<List<VentaDTO>> listarVentas() {
		List<VentaDTO> ventas = ventaService.getAll();
		if (ventas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(ventas);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> obtenerVenta(@PathVariable Integer id) {
		VentaDTO venta = ventaService.getById(id);
		if (venta == null) {
			return ResponseEntity.status(404).body("❌ Venta no encontrada.");
		}
		return ResponseEntity.ok(venta);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> actualizarVenta(@PathVariable Integer id, @RequestBody VentaDTO newData) {
		int result = ventaService.updateById(id, newData);

		switch (result) {
		case 0:
			return ResponseEntity.ok("✅ Venta actualizada correctamente.");
		case 1:
			return ResponseEntity.badRequest().body("❌ Error: Empleado no encontrado.");
		case 2:
			return ResponseEntity.badRequest().body("❌ Error: Factura no encontrada.");
		case 3:
			return ResponseEntity.status(404).body("❌ Venta no encontrada.");
		default:
			return ResponseEntity.internalServerError().body("⚠️ Error desconocido al actualizar la venta.");
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminarVenta(@PathVariable Integer id) {
		int result = ventaService.deleteById(id);

		switch (result) {
		case 0:
			return ResponseEntity.ok("✅ Venta eliminada correctamente.");
		case 1:
			return ResponseEntity.status(404).body("❌ Venta no encontrada.");
		default:
			return ResponseEntity.internalServerError().body("⚠️ Error desconocido al eliminar la venta.");
		}
	}

	@GetMapping("/contar")
	public ResponseEntity<Long> contarVentas() {
		return ResponseEntity.ok(ventaService.count());
	}

	@GetMapping("/existe/{id}")
	public ResponseEntity<String> existeVenta(@PathVariable Integer id) {
		boolean existe = ventaService.exists(id);
		return ResponseEntity.ok(existe ? "✅ La venta existe." : "❌ La venta no existe.");
	}
}

package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.FacturaDTO;
import co.edu.unbosque.retazoTextil.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {

	@Autowired
	private FacturaService facturaService;

	@PostMapping("/crear")
	public ResponseEntity<String> createFactura(@RequestBody FacturaDTO facturaDTO) {
		int result = facturaService.create(facturaDTO);

		switch (result) {
		case 1:
			return ResponseEntity.badRequest().body("La venta asociada no existe.");
		default:
			return ResponseEntity.ok("Factura creada exitosamente.");
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<List<FacturaDTO>> listarFacturas() {
		List<FacturaDTO> facturas = facturaService.getAll();
		return ResponseEntity.ok(facturas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getFacturaById(@PathVariable Integer id) {
		FacturaDTO factura = facturaService.getById(id);
		if (factura == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(factura);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> updateFactura(@PathVariable Integer id, @RequestBody FacturaDTO newData) {
		int result = facturaService.updateById(id, newData);

		switch (result) {
		case 1:
			return ResponseEntity.badRequest().body("La venta especificada no existe.");
		case 2:
			return ResponseEntity.notFound().build();
		default:
			return ResponseEntity.ok("Factura actualizada correctamente.");
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> deleteFactura(@PathVariable Integer id) {
		int result = facturaService.deleteById(id);

		if (result == 1) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok("Factura eliminada correctamente.");
	}

	@GetMapping("/existe/{id}")
	public ResponseEntity<Boolean> existeFactura(@PathVariable Integer id) {
		boolean existe = facturaService.exist(id);
		return ResponseEntity.ok(existe);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countFacturas() {
		long count = facturaService.count();
		return ResponseEntity.ok(count);
	}
}

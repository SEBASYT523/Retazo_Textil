package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.AccederDTO;
import co.edu.unbosque.retazoTextil.service.AccederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acceso")
@CrossOrigin(origins = "*")
public class AccederController {

	@Autowired
	private AccederService accederService;

	@PostMapping("/crear")
	public ResponseEntity<String> create(@RequestBody AccederDTO dto) {
		int result = accederService.create(dto);

		switch (result) {
		case 1:
			return ResponseEntity.badRequest().body("La bodega especificada no existe.");
		case 2:
			return ResponseEntity.badRequest().body("El vendedor especificado no existe.");
		default:
			return ResponseEntity.ok("Acceso creado correctamente.");
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<List<AccederDTO>> listar() {
		List<AccederDTO> accesos = accederService.getAll();
		return ResponseEntity.ok(accesos);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<String> update(@RequestBody AccederDTO dto) {
		int result = accederService.updateById(dto);

		switch (result) {
		case 1:
			return ResponseEntity.status(404).body("El acceso no existe.");
		case 2:
			return ResponseEntity.badRequest().body("La nueva bodega no existe.");
		case 3:
			return ResponseEntity.badRequest().body("El nuevo vendedor no existe.");
		default:
			return ResponseEntity.ok("Acceso actualizado correctamente.");
		}
	}

	@DeleteMapping("/eliminar/{idBodega}/{idVendedor}")
	public ResponseEntity<String> delete(@PathVariable Integer idBodega, @PathVariable Integer idVendedor) {
		int result = accederService.deleteById(idBodega, idVendedor);

		if (result == 1) {
			return ResponseEntity.status(404).body("No se encontró el acceso especificado.");
		}

		return ResponseEntity.ok("Acceso eliminado correctamente.");
	}

	@GetMapping("/existe/{idBodega}/{idVendedor}")
	public ResponseEntity<Boolean> existe(@PathVariable Integer idBodega, @PathVariable Integer idVendedor) {
		boolean existe = accederService.exist(idBodega, idVendedor);
		return ResponseEntity.ok(existe);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> count() {
		long count = accederService.count();
		return ResponseEntity.ok(count);
	}
}

package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.LocalDTO;
import co.edu.unbosque.retazoTextil.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locales")
@CrossOrigin(origins = "*")
public class LocalController {

	@Autowired
	private LocalService localService;

	@PostMapping("/crear")
	public ResponseEntity<String> createLocal(@RequestBody LocalDTO localDTO) {
		localService.create(localDTO);
		return ResponseEntity.ok("Local creado exitosamente.");
	}

	@GetMapping("/listar")
	public ResponseEntity<List<LocalDTO>> listarLocales() {
		List<LocalDTO> locales = localService.getAll();
		return ResponseEntity.ok(locales);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getLocalById(@PathVariable Integer id) {
		LocalDTO local = localService.getById(id);
		if (local == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(local);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> updateLocal(@PathVariable Integer id, @RequestBody LocalDTO newData) {
		int result = localService.updateById(id, newData);

		if (result == 1) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok("Local actualizado correctamente.");
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> deleteLocal(@PathVariable Integer id) {
		int result = localService.deleteById(id);

		if (result == 1) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok("Local eliminado correctamente.");
	}

	@GetMapping("/existe/{id}")
	public ResponseEntity<Boolean> existeLocal(@PathVariable Integer id) {
		boolean existe = localService.exist(id);
		return ResponseEntity.ok(existe);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countLocales() {
		long count = localService.count();
		return ResponseEntity.ok(count);
	}
}

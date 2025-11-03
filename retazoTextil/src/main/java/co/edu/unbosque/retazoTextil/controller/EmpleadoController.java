package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.EmpleadoDTO;
import co.edu.unbosque.retazoTextil.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@PostMapping("/crear")
	public ResponseEntity<String> createEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
	 empleadoService.create(empleadoDTO);
		return ResponseEntity.ok("Empleado creado exitosamente.");
	}

	@GetMapping("/listar")
	public ResponseEntity<List<EmpleadoDTO>> listarEmpleados() {
		List<EmpleadoDTO> empleados = empleadoService.getAll();
		return ResponseEntity.ok(empleados);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEmpleadoById(@PathVariable Integer id) {
		EmpleadoDTO empleado = empleadoService.getById(id);
		if (empleado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empleado);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> updateEmpleado(@PathVariable Integer id, @RequestBody EmpleadoDTO newData) {
		int result = empleadoService.updateById(id, newData);

		if (result == 2) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok("Empleado actualizado exitosamente.");
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> deleteEmpleado(@PathVariable Integer id) {
		int result = empleadoService.deleteById(id);

		if (result == 1) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok("Empleado eliminado correctamente.");
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countEmpleados() {
		return ResponseEntity.ok(empleadoService.count());
	}

	@GetMapping("/existe/{id}")
	public ResponseEntity<Boolean> existeEmpleado(@PathVariable Integer id) {
		return ResponseEntity.ok(empleadoService.exist(id));
	}

	@PostMapping("/login")
	public ResponseEntity<String> validarCredenciales(@RequestParam Integer id, @RequestParam String password) {
		int result = empleadoService.validateCredentials(id, password);
		if (result == 0) {
			return ResponseEntity.ok("Credenciales válidas. Inicio de sesión exitoso.");
		}
		return ResponseEntity.status(401).body("Credenciales incorrectas.");
	}
}

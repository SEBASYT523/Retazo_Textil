package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.EmpleadoDTO;
import co.edu.unbosque.retazoTextil.dto.LoginDTO;
import co.edu.unbosque.retazoTextil.model.Empleado;
import co.edu.unbosque.retazoTextil.service.AdministradorService;
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

	@Autowired
	private AdministradorService adminServ;

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
	public ResponseEntity<?> validarCredenciales(@RequestBody EmpleadoDTO loginRequest) {
		Empleado result = empleadoService.validateCredentials(loginRequest.getIdEmpleado(),
				loginRequest.getContrasenia());

		if (result != null) {
			String rol;

			if (adminServ.getById(loginRequest.getIdEmpleado()) != null) {
				rol = "ADMIN";
			} else {
				rol = "VENDEDOR";
			}

			EmpleadoDTO empleadoDTO = new EmpleadoDTO();
			empleadoDTO.setIdEmpleado(result.getIdEmpleado());
			empleadoDTO.setPrimerNombreEmpleado(result.getPrimerNombreEmpleado());
			empleadoDTO.setPrimerApellidoEmpleado(result.getPrimerApellidoEmpleado());
			empleadoDTO.setSegundoNombreEmpleado(result.getSegundoNombreEmpleado());
			empleadoDTO.setSegundoApellidoEmpleado(result.getSegundoApellidoEmpleado());
			empleadoDTO.setTelefonoEmpleado(result.getTelefonoEmpleado());
			empleadoDTO.setDireccionEmpleado(result.getDireccionEmpleado());
			empleadoDTO.setPaisNacimiento(result.getPaisNacimiento());
			empleadoDTO.setCiudadNacimiento(result.getCiudadNacimiento());
			empleadoDTO.setFechaNacimiento(result.getFechaNacimiento());
			empleadoDTO.setFechaIngreso(result.getFechaIngreso());
			empleadoDTO.setSalario(result.getSalario());

			LoginDTO response = new LoginDTO(empleadoDTO, rol);
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.status(401).body("Credenciales incorrectas.");
	}

}

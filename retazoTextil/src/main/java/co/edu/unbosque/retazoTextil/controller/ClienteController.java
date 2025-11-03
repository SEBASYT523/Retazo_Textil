package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.ClienteDTO;
import co.edu.unbosque.retazoTextil.model.Cliente;
import co.edu.unbosque.retazoTextil.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping("/crear")
	public ResponseEntity<String> createCliente(@RequestBody ClienteDTO clienteDTO) {
		clienteService.create(clienteDTO);
		return ResponseEntity.ok("Cliente creado exitosamente.");
	}

	@GetMapping("/listar")
	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		List<ClienteDTO> clientes = clienteService.getAll();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
		ClienteDTO cliente = clienteService.getById(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<String> updateCliente(@PathVariable Integer id, @RequestBody ClienteDTO newData) {
		int result = clienteService.updateById(id, newData);

		if (result == 1) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok("Cliente actualizado correctamente.");
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable Integer id) {
		int result = clienteService.deleteById(id);

		if (result == 1) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok("Cliente eliminado correctamente.");
	}

	@GetMapping("/existe/{id}")
	public ResponseEntity<Boolean> existeCliente(@PathVariable Integer id) {
		boolean existe = clienteService.exist(id);
		return ResponseEntity.ok(existe);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countClientes() {
		long count = clienteService.count();
		return ResponseEntity.ok(count);
	}

	@PostMapping("/login")
	public ResponseEntity<?> validarCredenciales(@RequestBody ClienteDTO loginRequest) {
		ClienteDTO result = clienteService.validateCredentials(loginRequest.getIdCliente(), loginRequest.getContrasenia());
		if (result != null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.status(401).body("Credenciales incorrectas.");
	}
}

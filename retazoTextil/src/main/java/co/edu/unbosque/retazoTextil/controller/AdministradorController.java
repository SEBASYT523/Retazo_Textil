package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.AdministradorDTO;
import co.edu.unbosque.retazoTextil.dto.AdministradorEmpleadoRequest;
import co.edu.unbosque.retazoTextil.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorServ;

    // ✅ Contar administradores
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(administradorServ.count());
    }

    // ✅ Listar todos los administradores
    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> getAll() {
        return ResponseEntity.ok(administradorServ.getAll());
    }

    // ✅ Obtener un administrador por su ID
    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> getById(@PathVariable Integer id) {
        AdministradorDTO dto = administradorServ.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    // ✅ Crear un nuevo administrador
    @PostMapping
    public ResponseEntity<String> create(@RequestBody AdministradorEmpleadoRequest request) {
        int result = administradorServ.create(request.getAdministrador(), request.getEmpleado());
        switch (result) {
            case 1:
                return ResponseEntity.badRequest().body("❌ El ID del empleado ya está en uso.");
            default:
                return ResponseEntity.ok("✅ Administrador creado correctamente.");
        }
    }

    // ✅ Actualizar un administrador existente
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Integer id,
            @RequestBody AdministradorEmpleadoRequest request) {

        int result = administradorServ.updateById(id, request.getAdministrador(), request.getEmpleado());
        switch (result) {
            case 2:
                return ((BodyBuilder) ResponseEntity.notFound()).body("❌ No se encontró el administrador con ID " + id);
            default:
                return ResponseEntity.ok("✅ Administrador actualizado correctamente.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        int result = administradorServ.deleteById(id);
        if (result == 1) {
            return ((BodyBuilder) ResponseEntity.notFound()).body("❌ No se encontró el administrador con ID " + id);
        }
        return ResponseEntity.ok("✅ Administrador eliminado correctamente.");
    }
    
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Integer id) {
        return ResponseEntity.ok(administradorServ.exist(id));
    }

}

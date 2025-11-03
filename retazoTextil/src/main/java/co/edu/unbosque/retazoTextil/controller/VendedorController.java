package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.VendedorDTO;
import co.edu.unbosque.retazoTextil.dto.VendedorEmpleadoRequest;
import co.edu.unbosque.retazoTextil.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorServ;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VendedorEmpleadoRequest request) {
        int result = vendedorServ.create(request.getVendedor(), request.getEmpleado());
        switch (result) {
            case 1:
                return ResponseEntity.badRequest().body(request);
            case 2:
                return ResponseEntity.badRequest().body("❌ Ya existe un vendedor con ese id.");
            case 3:
                return ResponseEntity.badRequest().body("❌ No se pudo crear el empleado asociado (id ya tomado o error).");
            case 4:
                return ResponseEntity.badRequest().body("❌ Local no encontrado.");
            default:
                return ResponseEntity.ok("✅ Vendedor creado correctamente.");
        }
    }

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> getAll() {
        List<VendedorDTO> dtos = vendedorServ.getAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        VendedorDTO dto = vendedorServ.getById(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Vendedor con id " + id + " no encontrado.");
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody VendedorEmpleadoRequest request) {
        int result = vendedorServ.updateById(id, request.getVendedor(), request.getEmpleado());
        switch (result) {
            case 1:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Vendedor con id " + id + " no encontrado.");
            case 2:
                return ResponseEntity.badRequest().body("❌ Local indicado no existe.");
            case 3:
                return ResponseEntity.badRequest().body("❌ Error actualizando empleado asociado.");
            default:
                return ResponseEntity.ok("✅ Vendedor actualizado correctamente.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        int result = vendedorServ.deleteById(id);
        if (result != 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Vendedor con id " + id + " no encontrado.");
        }
        return ResponseEntity.ok("✅ Vendedor eliminado correctamente.");
    }
}

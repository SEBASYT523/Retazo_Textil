package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.ProveedorDTO;
import co.edu.unbosque.retazoTextil.service.ProveedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin(origins = "*") 
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ProveedorDTO dto) {
        int result = proveedorService.create(dto);
        switch (result) {
            case 1:
                return ResponseEntity.badRequest().body("Error: El administrador especificado no existe.");
            default:
                return ResponseEntity.ok("Proveedor creado correctamente.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProveedorDTO>> getAll() {
        List<ProveedorDTO> proveedores = proveedorService.getAll();
        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        ProveedorDTO dto = proveedorService.getById(id);
        if (dto == null) {
            return ResponseEntity.status(404).body("Error: No se encontró el proveedor con id " + id);
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody ProveedorDTO dto) {
        int result = proveedorService.updateById(id, dto);
        switch (result) {
            case 1:
                return ResponseEntity.badRequest().body("Error: El administrador especificado no existe.");
            case 3:
                return ResponseEntity.status(404).body("Error: El proveedor no existe.");
            default:
                return ResponseEntity.ok("Proveedor actualizado correctamente.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        int result = proveedorService.deleteById(id);
        if (result == 1) {
            return ResponseEntity.status(404).body("Error: El proveedor no existe.");
        }
        return ResponseEntity.ok("Proveedor eliminado correctamente.");
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Integer id) {
        return ResponseEntity.ok(proveedorService.exist(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(proveedorService.count());
    }
}

package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.ContactarDTO;
import co.edu.unbosque.retazoTextil.service.ContactarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactar")
@CrossOrigin(origins = "*")
public class ContactarController {

    @Autowired
    private ContactarService contactarServ;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ContactarDTO dto) {
        int result = contactarServ.create(dto);
        switch (result) {
            case 1:
                return ResponseEntity.badRequest().body("❌ El proveedor especificado no existe.");
            case 2:
                return ResponseEntity.badRequest().body("❌ El administrador especificado no existe.");
            default:
                return ResponseEntity.ok("✅ Relación creada correctamente entre proveedor y administrador.");
        }
    }

    @GetMapping
    public ResponseEntity<List<ContactarDTO>> getAll() {
        return ResponseEntity.ok(contactarServ.getAll());
    }

    @DeleteMapping("/{proveedorId}/{administradorId}")
    public ResponseEntity<String> deleteById(
            @PathVariable Integer proveedorId,
            @PathVariable Integer administradorId) {

        int result = contactarServ.deleteById(proveedorId, administradorId);
        if (result == 1) {
            return ((BodyBuilder) ResponseEntity.notFound()).body("❌ No se encontró la relación con proveedor ID " + proveedorId +
                    " y administrador ID " + administradorId);
        }
        return ResponseEntity.ok("✅ Relación eliminada correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ContactarDTO dto) {
        int result = contactarServ.updateById(dto);
        switch (result) {
            case 1:
                return ((BodyBuilder) ResponseEntity.notFound()).body("❌ No se encontró la relación especificada.");
            case 2:
                return ResponseEntity.badRequest().body("❌ El nuevo proveedor especificado no existe.");
            case 3:
                return ResponseEntity.badRequest().body("❌ El nuevo administrador especificado no existe.");
            default:
                return ResponseEntity.ok("✅ Relación actualizada correctamente.");
        }
    }

    @GetMapping("/exists/{proveedorId}/{administradorId}")
    public ResponseEntity<Boolean> exists(
            @PathVariable Integer proveedorId,
            @PathVariable Integer administradorId) {
        return ResponseEntity.ok(contactarServ.exist(proveedorId, administradorId));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(contactarServ.count());
    }
}

package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.GuardarDTO;
import co.edu.unbosque.retazoTextil.service.GuardarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardar")
@CrossOrigin(origins = "*")
public class GuardarController {

    @Autowired
    private GuardarService guardarServ;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody GuardarDTO dto) {
        int result = guardarServ.create(dto);
        switch (result) {
            case 1:
                return ResponseEntity.badRequest().body("❌ La bodega especificada no existe.");
            case 2:
                return ResponseEntity.badRequest().body("❌ El producto especificado no existe.");
            default:
                return ResponseEntity.ok("✅ Relación creada correctamente entre bodega y producto.");
        }
    }

    @GetMapping
    public ResponseEntity<List<GuardarDTO>> getAll() {
        return ResponseEntity.ok(guardarServ.getAll());
    }

    @DeleteMapping("/{bodegaId}/{productoId}")
    public ResponseEntity<String> deleteById(
            @PathVariable Integer bodegaId,
            @PathVariable Integer productoId) {

        int result = guardarServ.deleteById(bodegaId, productoId);
        if (result == 1) {
            return ((BodyBuilder) ResponseEntity.notFound()).body("❌ No se encontró la relación con bodega ID " + bodegaId +
                    " y producto ID " + productoId);
        }
        return ResponseEntity.ok("✅ Relación eliminada correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody GuardarDTO dto) {
        int result = guardarServ.updateById(dto);
        switch (result) {
            case 1:
                return ((BodyBuilder) ResponseEntity.notFound()).body("❌ No se encontró la relación especificada.");
            case 2:
                return ResponseEntity.badRequest().body("❌ La nueva bodega especificada no existe.");
            case 3:
                return ResponseEntity.badRequest().body("❌ El nuevo producto especificado no existe.");
            default:
                return ResponseEntity.ok("✅ Relación actualizada correctamente.");
        }
    }

    @GetMapping("/exists/{bodegaId}/{productoId}")
    public ResponseEntity<Boolean> exists(
            @PathVariable Integer bodegaId,
            @PathVariable Integer productoId) {
        return ResponseEntity.ok(guardarServ.exist(bodegaId, productoId));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(guardarServ.count());
    }
}

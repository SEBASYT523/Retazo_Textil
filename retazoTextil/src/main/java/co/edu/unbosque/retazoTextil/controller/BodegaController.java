package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.BodegaDTO;
import co.edu.unbosque.retazoTextil.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodegas")
@CrossOrigin(origins = "*")
public class BodegaController {

    @Autowired
    private BodegaService bodegaServ;

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(bodegaServ.count());
    }

    @GetMapping
    public ResponseEntity<List<BodegaDTO>> getAll() {
        return ResponseEntity.ok(bodegaServ.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodegaDTO> getById(@PathVariable Integer id) {
        BodegaDTO dto = bodegaServ.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody BodegaDTO bodega) {
        int result = bodegaServ.create(bodega);
        switch (result) {
            case 1:
                return ResponseEntity.badRequest().body("❌ El local asociado no existe.");
            default:
                return ResponseEntity.ok("✅ Bodega creada correctamente.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody BodegaDTO newData) {
        int result = bodegaServ.updateById(id, newData);
        switch (result) {
            case 2:
                return ((BodyBuilder) ResponseEntity.notFound()).body("❌ No se encontró la bodega con ID " + id);
            default:
                return ResponseEntity.ok("✅ Bodega actualizada correctamente.");
        }
    }

    // ✅ Eliminar una bodega
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        int result = bodegaServ.deleteById(id);
        if (result == 1) {
            return ((BodyBuilder) ResponseEntity.notFound()).body("❌ No se encontró la bodega con ID " + id);
        }
        return ResponseEntity.ok("✅ Bodega eliminada correctamente.");
    }

    // ✅ Verificar si una bodega existe por ID
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Integer id) {
        return ResponseEntity.ok(bodegaServ.exist(id));
    }
}

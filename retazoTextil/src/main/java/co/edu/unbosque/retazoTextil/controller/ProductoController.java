package co.edu.unbosque.retazoTextil.controller;

import co.edu.unbosque.retazoTextil.dto.ProductoDTO;
import co.edu.unbosque.retazoTextil.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*") 
public class ProductoController {

    @Autowired
    private ProductoService productoService;

  
    @PostMapping("/crear")
    public ResponseEntity<String> create(@RequestBody ProductoDTO dto) {
        int result = productoService.create(dto);
        switch (result) {
            case 1:
                return ResponseEntity.badRequest().body("❌ Error: proveedor no encontrado.");
            case 0:
                return ResponseEntity.ok("✅ Producto creado correctamente.");
            default:
                return ResponseEntity.internalServerError().body("⚠️ Error desconocido al crear el producto.");
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDTO>> getAll() {
        List<ProductoDTO> productos = productoService.getAll();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        ProductoDTO dto = productoService.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> updateById(@PathVariable Integer id, @RequestBody ProductoDTO dto) {
        int result = productoService.updateById(id, dto);
        switch (result) {
            case 0:
                return ResponseEntity.ok("✅ Producto actualizado correctamente.");
            case 1:
                return ResponseEntity.badRequest().body("❌ Proveedor no encontrado.");
            case 2:
                return ResponseEntity.badRequest().body("❌ Producto no encontrado.");
            default:
                return ResponseEntity.internalServerError().body("⚠️ Error desconocido al actualizar el producto.");
        }
    }

  
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        int result = productoService.deleteById(id);
        if (result == 0) {
            return ResponseEntity.ok("🗑️ Producto eliminado correctamente.");
        } else {
            return ResponseEntity.badRequest().body("❌ Producto no encontrado.");
        }
    }

  
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> exist(@PathVariable Integer id) {
        boolean existe = productoService.exist(id);
        return ResponseEntity.ok(existe);
    }

   
    @GetMapping("/contar")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(productoService.count());
    }
}

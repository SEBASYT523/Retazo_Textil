package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.ProductoDTO;
import co.edu.unbosque.retazoTextil.model.Producto;
import co.edu.unbosque.retazoTextil.model.Guardar;
import co.edu.unbosque.retazoTextil.model.Proveedor;
import co.edu.unbosque.retazoTextil.repository.GuardarRepository;
import co.edu.unbosque.retazoTextil.repository.ProductoRepository;
import co.edu.unbosque.retazoTextil.repository.ProveedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private ProveedorRepository proveedorRepo;

    @Autowired
    private GuardarRepository guardarRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(ProductoDTO data) {
        Optional<Proveedor> optProveedor = proveedorRepo.findById(data.getProveedorId());
        if (optProveedor.isEmpty()) {
            return 1; 
        }

        Producto producto = new Producto();
        producto.setNombre(data.getNombre());
        producto.setTipoProducto(data.getTipoProducto());
        producto.setColor(data.getColor());
        producto.setPrecio(data.getPrecio());
        producto.setProveedor(optProveedor.get());

        if (data.getGuardadosIds() != null && !data.getGuardadosIds().isEmpty()) {
            List<Guardar> guardados = data.getGuardadosIds().stream()
                    .map(gid -> guardarRepo.findById(gid).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            producto.setGuardados(guardados);
        }

        productoRepo.save(producto);
        return 0;
    }

    public List<ProductoDTO> getAll() {
        List<Producto> productos = productoRepo.findAll();
        return productos.stream().map(p -> {
            ProductoDTO dto = modelMapper.map(p, ProductoDTO.class);
            dto.setProveedorId(p.getProveedor() != null ? p.getProveedor().getIdProveedor() : null);
            if (p.getGuardados() != null) {
                dto.setGuardadosIds(p.getGuardados().stream()
                        .map(Guardar::getId)
                        .collect(Collectors.toList()));
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public ProductoDTO getById(Integer id) {
        return productoRepo.findById(id)
                .map(p -> {
                    ProductoDTO dto = modelMapper.map(p, ProductoDTO.class);
                    dto.setProveedorId(p.getProveedor() != null ? p.getProveedor().getIdProveedor() : null);
                    if (p.getGuardados() != null) {
                        dto.setGuardadosIds(p.getGuardados().stream()
                                .map(Guardar::getId)
                                .collect(Collectors.toList()));
                    }
                    return dto;
                })
                .orElse(null);
    }

    public int updateById(Integer id, ProductoDTO newData) {
        Optional<Producto> optProducto = productoRepo.findById(id);
        if (optProducto.isEmpty()) {
            return 2; 
        }

        Producto producto = optProducto.get();

        if (newData.getNombre() != null) producto.setNombre(newData.getNombre());
        if (newData.getTipoProducto() != null) producto.setTipoProducto(newData.getTipoProducto());
        if (newData.getColor() != null) producto.setColor(newData.getColor());
        if (newData.getPrecio() != null) producto.setPrecio(newData.getPrecio());

        if (newData.getProveedorId() != null) {
            Optional<Proveedor> optProveedor = proveedorRepo.findById(newData.getProveedorId());
            if (optProveedor.isEmpty()) {
                return 1; 
            }
            producto.setProveedor(optProveedor.get());
        }

        if (newData.getGuardadosIds() != null) {
            List<Guardar> guardados = newData.getGuardadosIds().stream()
                    .map(gid -> guardarRepo.findById(gid).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            producto.setGuardados(guardados);
        }

        productoRepo.save(producto);
        return 0;
    }

    public int deleteById(Integer id) {
        return productoRepo.findById(id).map(p -> {
            productoRepo.delete(p);
            return 0;
        }).orElse(1);
    }

    public boolean exist(Integer id) {
        return productoRepo.existsById(id);
    }

    public long count() {
        return productoRepo.count();
    }
}

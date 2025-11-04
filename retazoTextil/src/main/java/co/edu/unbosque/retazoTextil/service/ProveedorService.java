package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.ProveedorDTO;
import co.edu.unbosque.retazoTextil.model.*;
import co.edu.unbosque.retazoTextil.repository.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepo;

    @Autowired
    private AdministradorRepository adminRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private ContactarRepository contactarRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(ProveedorDTO data) {

        Proveedor proveedor = new Proveedor();
       
        Administrador admin = adminRepo.getById(1);
        proveedor.setAdministrador(admin);
        proveedor.setPrimerNombreProveedor(data.getPrimerNombreProveedor());
        proveedor.setSegundoNombreProveedor(data.getSegundoNombreProveedor());
        proveedor.setPrimerApellidoProveedor(data.getPrimerApellidoProveedor());
        proveedor.setSegundoApellidoProveedor(data.getSegundoApellidoProveedor());
        proveedor.setTelefono(data.getTelefono());
        proveedor.setCiudad(data.getCiudad());
        proveedor.setPais(data.getPais());

        proveedorRepo.save(proveedor);
        return 0;
    }

    public List<ProveedorDTO> getAll() {
        List<Proveedor> entities = proveedorRepo.findAll();
        List<ProveedorDTO> dtos = new ArrayList<>();

        for (Proveedor p : entities) {
            ProveedorDTO dto = modelMapper.map(p, ProveedorDTO.class);

            dto.setInteger(p.getAdministrador().getNumeroCubiculo());

            List<Integer> productosId = new ArrayList<>();
            for (Producto prod : p.getProductos()) {
                productosId.add(prod.getCodProducto());
            }
            dto.setIntegers(productosId);

            List<ContactarId> contactosId = new ArrayList<>();
            for (Contactar con : p.getContactos()) {
                contactosId.add(con.getId());
            }
            dto.setContactos(contactosId);

            dtos.add(dto);
        }

        return dtos;
    }

    public ProveedorDTO getById(Integer id) {
        Optional<Proveedor> opt = proveedorRepo.findById(id);
        if (opt.isEmpty()) {
            return null;
        }

        Proveedor p = opt.get();
        ProveedorDTO dto = modelMapper.map(p, ProveedorDTO.class);

        dto.setInteger(p.getAdministrador().getNumeroCubiculo());

        List<Integer> productosId = new ArrayList<>();
        for (Producto prod : p.getProductos()) {
            productosId.add(prod.getCodProducto());
        }
        dto.setIntegers(productosId);

        List<ContactarId> contactosId = new ArrayList<>();
        for (Contactar con : p.getContactos()) {
            contactosId.add(con.getId());
        }
        dto.setContactos(contactosId);

        return dto;
    }

    public int updateById(Integer id, ProveedorDTO newData) {
        Optional<Proveedor> optProveedor = proveedorRepo.findById(id);
        if (optProveedor.isEmpty()) {
            return 3; 
        }

        Proveedor proveedor = optProveedor.get();

        if (newData.getPrimerNombreProveedor() != null)
            proveedor.setPrimerNombreProveedor(newData.getPrimerNombreProveedor());

        if (newData.getSegundoNombreProveedor() != null)
            proveedor.setSegundoNombreProveedor(newData.getSegundoNombreProveedor());

        if (newData.getPrimerApellidoProveedor() != null)
            proveedor.setPrimerApellidoProveedor(newData.getPrimerApellidoProveedor());

        if (newData.getSegundoApellidoProveedor() != null)
            proveedor.setSegundoApellidoProveedor(newData.getSegundoApellidoProveedor());

        if (newData.getTelefono() != null)
            proveedor.setTelefono(newData.getTelefono());

        if (newData.getCiudad() != null)
            proveedor.setCiudad(newData.getCiudad());

        if (newData.getPais() != null)
            proveedor.setPais(newData.getPais());

        if (newData.getInteger() != null) {
            Optional<Administrador> optAdmin = adminRepo.findById(newData.getInteger());
            if (optAdmin.isEmpty()) {
                return 1;
            }
            proveedor.setAdministrador(optAdmin.get());
        }

        if (newData.getIntegers() != null) {
            List<Producto> productos = new ArrayList<>();
            for (Integer idProd : newData.getIntegers()) {
                productoRepo.findById(idProd).ifPresent(productos::add);
            }
            proveedor.setProductos(productos);
        }

        if (newData.getContactos() != null) {
            List<Contactar> contactos = new ArrayList<>();
            for (ContactarId idCon : newData.getContactos()) {
                contactarRepo.findById(idCon).ifPresent(contactos::add);
            }
            proveedor.setContactos(contactos);
        }

        proveedorRepo.save(proveedor);
        return 0;
    }

    public int deleteById(Integer id) {
        Optional<Proveedor> opt = proveedorRepo.findById(id);
        if (opt.isEmpty()) {
            return 1; 
        }

        proveedorRepo.delete(opt.get());
        return 0;
    }

    public boolean exist(Integer id) {
        return proveedorRepo.existsById(id);
    }

    public long count() {
        return proveedorRepo.count();
    }
}

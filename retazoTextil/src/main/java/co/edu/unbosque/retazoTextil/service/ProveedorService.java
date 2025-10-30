package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.ProveedorDTO;
import co.edu.unbosque.retazoTextil.model.*;
import co.edu.unbosque.retazoTextil.repository.*;
import co.edu.unbosque.retazoTextil.util.AESUtil;

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
        Optional<Administrador> optAdmin = adminRepo.findById(data.getInteger());
        if (optAdmin.isEmpty()) {
            return 1; 
        }

        Proveedor proveedor = new Proveedor();

        proveedor.setPrimerNombreProveedor(AESUtil.encrypt(data.getPrimerNombreProveedor()));
        proveedor.setSegundoNombreProveedor(AESUtil.encrypt(data.getSegundoNombreProveedor()));
        proveedor.setPrimerApellidoProveedor(AESUtil.encrypt(data.getPrimerApellidoProveedor()));
        proveedor.setSegundoApellidoProveedor(AESUtil.encrypt(data.getSegundoApellidoProveedor()));
        proveedor.setTelefono(AESUtil.encrypt(data.getTelefono()));
        proveedor.setCiudad(AESUtil.encrypt(data.getCiudad()));
        proveedor.setPais(AESUtil.encrypt(data.getPais()));

        proveedor.setAdministrador(optAdmin.get());

        List<Producto> productos = new ArrayList<>();
        if (data.getIntegers() != null) {
            for (Integer idProd : data.getIntegers()) {
                productoRepo.findById(idProd).ifPresent(productos::add);
            }
        }
        proveedor.setProductos(productos);

        List<Contactar> contactos = new ArrayList<>();
        if (data.getContactos() != null) {
            for (ContactarId idCon : data.getContactos()) {
                contactarRepo.findById(idCon).ifPresent(contactos::add);
            }
        }
        proveedor.setContactos(contactos);

        proveedorRepo.save(proveedor);
        return 0; 
    }

    public List<ProveedorDTO> getAll() {
        List<Proveedor> entities = proveedorRepo.findAll();
        List<ProveedorDTO> dtos = new ArrayList<>();

        for (Proveedor p : entities) {
            ProveedorDTO dto = modelMapper.map(p, ProveedorDTO.class);

            dto.setPrimerNombreProveedor(AESUtil.decrypt(p.getPrimerNombreProveedor()));
            dto.setSegundoNombreProveedor(AESUtil.decrypt(p.getSegundoNombreProveedor()));
            dto.setPrimerApellidoProveedor(AESUtil.decrypt(p.getPrimerApellidoProveedor()));
            dto.setSegundoApellidoProveedor(AESUtil.decrypt(p.getSegundoApellidoProveedor()));
            dto.setTelefono(AESUtil.decrypt(p.getTelefono()));
            dto.setCiudad(AESUtil.decrypt(p.getCiudad()));
            dto.setPais(AESUtil.decrypt(p.getPais()));

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

        dto.setPrimerNombreProveedor(AESUtil.decrypt(p.getPrimerNombreProveedor()));
        dto.setSegundoNombreProveedor(AESUtil.decrypt(p.getSegundoNombreProveedor()));
        dto.setPrimerApellidoProveedor(AESUtil.decrypt(p.getPrimerApellidoProveedor()));
        dto.setSegundoApellidoProveedor(AESUtil.decrypt(p.getSegundoApellidoProveedor()));
        dto.setTelefono(AESUtil.decrypt(p.getTelefono()));
        dto.setCiudad(AESUtil.decrypt(p.getCiudad()));
        dto.setPais(AESUtil.decrypt(p.getPais()));

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
            proveedor.setPrimerNombreProveedor(AESUtil.encrypt(newData.getPrimerNombreProveedor()));

        if (newData.getSegundoNombreProveedor() != null)
            proveedor.setSegundoNombreProveedor(AESUtil.encrypt(newData.getSegundoNombreProveedor()));

        if (newData.getPrimerApellidoProveedor() != null)
            proveedor.setPrimerApellidoProveedor(AESUtil.encrypt(newData.getPrimerApellidoProveedor()));

        if (newData.getSegundoApellidoProveedor() != null)
            proveedor.setSegundoApellidoProveedor(AESUtil.encrypt(newData.getSegundoApellidoProveedor()));

        if (newData.getTelefono() != null)
            proveedor.setTelefono(AESUtil.encrypt(newData.getTelefono()));

        if (newData.getCiudad() != null)
            proveedor.setCiudad(AESUtil.encrypt(newData.getCiudad()));

        if (newData.getPais() != null)
            proveedor.setPais(AESUtil.encrypt(newData.getPais()));

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

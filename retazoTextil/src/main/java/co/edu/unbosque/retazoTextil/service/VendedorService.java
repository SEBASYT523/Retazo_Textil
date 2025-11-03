package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.VendedorDTO;
import co.edu.unbosque.retazoTextil.dto.EmpleadoDTO;
import co.edu.unbosque.retazoTextil.model.Vendedor;
import co.edu.unbosque.retazoTextil.model.Empleado;
import co.edu.unbosque.retazoTextil.model.Local;
import co.edu.unbosque.retazoTextil.repository.VendedorRepository;
import co.edu.unbosque.retazoTextil.repository.LocalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.*;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepo;

    @Autowired
    private LocalRepository localRepo;

    @Autowired
    private EmpleadoService empleadoServ;

    @Autowired
    private ModelMapper modelMapper;

    public long count() {
        return vendedorRepo.count();
    }

    public boolean exist(Integer id) {
        return vendedorRepo.existsById(id);
    }

    /**
     * Crea un vendedor junto con su empleado asociado.
     * Devuelve:
     * 0 -> ok
     * 1 -> datos nulos
     * 2 -> vendedor con ese id ya existe
     * 3 -> el empleado no pudo crearse (id ya tomado u otro error)
     * 4 -> local no encontrado
     */
    @Transactional
    public int create(VendedorDTO data, EmpleadoDTO empleadoDTO) {
        if (data == null || empleadoDTO == null) {
            return 1;
        }

        // Si ya hay un vendedor con ese id
        if (vendedorRepo.findById(data.getIdEmpleado()).isPresent()) {
            return 2;
        }

        // Si ya existe un empleado con ese id, detener (o podrías usarlo en su lugar)
        if (empleadoServ.findIdAlreadyTaken(empleadoDTO.getIdEmpleado())) {
            return 3;
        }

        Optional<Local> optLocal = localRepo.findById(data.getLocalId());
        if (optLocal.isEmpty()) {
            return 4;
        }

        // Crear la entidad Empleado (usar create o map + save según tu preferencia)
        // Si tu EmpleadoService.create() ya persiste y retorna la entidad gestionada, úsala:
        Empleado empleadoGuardado = empleadoServ.create(empleadoDTO);
        if (empleadoGuardado == null) {
            return 3;
        }

        // Crear Vendedor y enlazar ambas instancias (usar la misma instancia de empleado)
        Vendedor vendedor = new Vendedor();
        vendedor.setNumeroProductosVendidos(data.getNumeroProductosVendidos());
        vendedor.setTotalVentas(data.getTotalVentas());
        vendedor.setLocal(optLocal.get());

        vendedor.setEmpleado(empleadoGuardado);
        empleadoGuardado.setVendedor(vendedor);

        // Guardar desde el lado con cascade (Empleado tiene cascade = ALL hacia Vendedor)
        // EmpleadoService debe tener saveEntity para hacer empleadoRepo.save(...)
        empleadoServ.saveEntity(empleadoGuardado);

        return 0;
    }

    public List<VendedorDTO> getAll() {
        List<Vendedor> vendedores = vendedorRepo.findAll();
        List<VendedorDTO> dtos = new ArrayList<>();

        for (Vendedor v : vendedores) {
            VendedorDTO dto = modelMapper.map(v, VendedorDTO.class);
            dto.setIdEmpleado(v.getEmpleado() != null ? v.getEmpleado().getIdEmpleado() : null);
            dto.setLocalId(v.getLocal() != null ? v.getLocal().getNumeroLocal() : null);
            dtos.add(dto);
        }
        return dtos;
    }

    public VendedorDTO getById(Integer id) {
        Optional<Vendedor> opt = vendedorRepo.findById(id);
        if (opt.isEmpty()) {
            return null;
        }

        Vendedor v = opt.get();
        VendedorDTO dto = modelMapper.map(v, VendedorDTO.class);
        dto.setIdEmpleado(v.getEmpleado() != null ? v.getEmpleado().getIdEmpleado() : null);
        dto.setLocalId(v.getLocal() != null ? v.getLocal().getNumeroLocal() : null);
        return dto;
    }

    /**
     * Actualiza vendedor y su empleado asociado.
     * Retorna:
     * 0 -> ok
     * 1 -> vendedor no existe
     * 2 -> local no existe (si se intenta cambiar)
     * 3 -> error actualizando empleado
     */
    @Transactional
    public int updateById(Integer id, VendedorDTO newData, EmpleadoDTO empleadoData) {
        Optional<Vendedor> opt = vendedorRepo.findById(id);
        if (opt.isEmpty()) {
            return 1;
        }

        Vendedor vendedor = opt.get();
        Empleado empleado = vendedor.getEmpleado();
        if (empleado == null) {
            return 3; // inconsistencia: vendedor sin empleado
        }

        // Actualiza empleado vía servicio (tu método existente hace find + save)
        int empResult = empleadoServ.updateById(empleado.getIdEmpleado(), empleadoData);
        if (empResult != 0) {
            return 3;
        }

        // Recupera la entidad gestionada (misma instancia en sesión)
        Empleado managedEmpleado = empleadoServ.getEntityById(empleado.getIdEmpleado());
        if (managedEmpleado == null) {
            return 3;
        }

        // Asegura la bidireccionalidad
        vendedor.setEmpleado(managedEmpleado);
        managedEmpleado.setVendedor(vendedor);

        // Si cambian datos del vendedor (p. ej. local), valídalo
        if (newData != null) {
            if (newData.getLocalId() != null) {
                Optional<Local> optLocal = localRepo.findById(newData.getLocalId());
                if (optLocal.isEmpty()) {
                    return 2;
                }
                vendedor.setLocal(optLocal.get());
            }

            if (newData.getNumeroProductosVendidos() != null) {
                vendedor.setNumeroProductosVendidos(newData.getNumeroProductosVendidos());
            }
            if (newData.getTotalVentas() != null) {
                vendedor.setTotalVentas(newData.getTotalVentas());
            }
        }

        // Guardar (puede no ser estrictamente necesario si Empleado tiene cascade, pero lo hacemos explícito)
        vendedorRepo.save(vendedor);
        return 0;
    }

    public int deleteById(Integer id) {
        Optional<Vendedor> opt = vendedorRepo.findById(id);
        if (opt.isEmpty()) {
            return 1;
        }

        vendedorRepo.delete(opt.get());
        return 0;
    }
}

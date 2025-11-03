package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.AdministradorDTO;
import co.edu.unbosque.retazoTextil.dto.EmpleadoDTO;
import co.edu.unbosque.retazoTextil.model.Administrador;
import co.edu.unbosque.retazoTextil.model.Contactar;
import co.edu.unbosque.retazoTextil.model.ContactarId;
import co.edu.unbosque.retazoTextil.model.Empleado;
import co.edu.unbosque.retazoTextil.model.Proveedor;
import co.edu.unbosque.retazoTextil.repository.AdministradorRepository;
import co.edu.unbosque.retazoTextil.util.AESUtil;
import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
	@Autowired
	private AdministradorRepository AdministradorRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmpleadoService empleadoServ;

	public AdministradorService(AdministradorRepository AdministradorRepo, ModelMapper modelMapper) {
		this.AdministradorRepo = AdministradorRepo;
		this.modelMapper = modelMapper;

	}

	public long count() {
		return AdministradorRepo.count();
	}

	public boolean exist(Integer id) {
		return AdministradorRepo.existsById(id);
	}

	
	@Transactional
	public int create(AdministradorDTO adminDto, EmpleadoDTO empleadoDto) {
	    if (empleadoServ.findIdAlreadyTaken(empleadoDto.getIdEmpleado())) {
	        return 1; 
	    }

	    Empleado nuevoEmpleado = modelMapper.map(empleadoDto, Empleado.class);
	    nuevoEmpleado.setContrasenia(AESUtil.hashinBCrypt(empleadoDto.getContrasenia()));

	    Administrador administrador = new Administrador();
	    administrador.setNumeroCubiculo(adminDto.getNumeroCubiculo());

	    administrador.setEmpleado(nuevoEmpleado);
	    nuevoEmpleado.setAdministrador(administrador);

	    empleadoServ.saveEntity(nuevoEmpleado);

	    return 0;
	}

	public List<AdministradorDTO> getAll() {
		List<Administrador> entities = AdministradorRepo.findAll();
		List<AdministradorDTO> dtos = new ArrayList<>();
		for (Administrador u : entities) {

			AdministradorDTO dto = new AdministradorDTO();

			dto.setIdEmpleado(u.getIdEmpleado());
			dto.setNumeroCubiculo(u.getNumeroCubiculo());
			if (u.getProveedores() != null) {
				List<Integer> proveedoresIds = new ArrayList<>();
				for (Proveedor p : u.getProveedores()) {
					proveedoresIds.add(p.getIdProveedor());
				}
				dto.setProveedoresId(proveedoresIds);
			}

			if (u.getContactos() != null) {
				List<ContactarId> contactosIds = new ArrayList<>();
				for (Contactar c : u.getContactos()) {
					contactosIds.add(c.getId());
				}
				dto.setContactosId(contactosIds);
			}

			dtos.add(dto);
		}
		return dtos;
	}

	public int deleteById(Integer id) {
		return AdministradorRepo.findById(id).map(u -> {
			AdministradorRepo.delete(u);
			return 0;
		}).orElse(1);
	}

	 @Transactional
	    public int updateById(Integer id, AdministradorDTO newData, EmpleadoDTO empleadoDto) {
	        Optional<Administrador> opt = AdministradorRepo.findById(id);
	        if (opt.isEmpty()) {
	            return 2; 
	        }

	        int empResult = empleadoServ.updateById(id, empleadoDto);
	        if (empResult != 0) {
	            return 3;
	        }

	        Administrador admin = opt.get();

	        Empleado managedEmpleado = empleadoServ.getEntityById(id);
	        if (managedEmpleado == null) {
	            return 3; 
	        }

	        admin.setEmpleado(managedEmpleado);
	        managedEmpleado.setAdministrador(admin); 

	        if (newData.getNumeroCubiculo() != null) {
	            admin.setNumeroCubiculo(newData.getNumeroCubiculo());
	        }

	       
	        AdministradorRepo.save(admin);

	        return 0;
	    }

	public AdministradorDTO getById(Integer id) {
		return AdministradorRepo.findById(id).map(u -> {

			return modelMapper.map(u, AdministradorDTO.class);
		}).orElse(null);
	}

	public boolean findIdAlreadyTaken(Integer id) {
		return AdministradorRepo.findById(id).isPresent();
	}

}

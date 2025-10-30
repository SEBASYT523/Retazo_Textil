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

	public int create(AdministradorDTO data, EmpleadoDTO empleado) {
		if (findIdAlreadyTaken(data.getIdEmpleado())) {
			return 1;
		}
		empleadoServ.create(empleado);
		Administrador entity = new Administrador();

		entity.setEmpleado(modelMapper.map(empleado, Empleado.class));
        entity.setNumeroCubiculo(Integer.parseInt(AESUtil.encrypt(data.getNumeroCubiculo().toString())));
        
		AdministradorRepo.save(entity);
		return 0;
	}

	public List<AdministradorDTO> getAll() {
		List<Administrador> entities = AdministradorRepo.findAll();
		List<AdministradorDTO> dtos = new ArrayList<>();
		for (Administrador u : entities) {
			Empleado e = u.getEmpleado();
			
			e.setDireccionEmpleado(AESUtil.decrypt(e.getDireccionEmpleado()));
			e.setTelefonoEmpleado(AESUtil.decrypt(e.getTelefonoEmpleado()));
			e.setPrimerNombreEmpleado(AESUtil.decrypt(e.getPrimerNombreEmpleado()));
			e.setSegundoNombreEmpleado(AESUtil.decrypt(e.getSegundoNombreEmpleado()));
			e.setPrimerApellidoEmpleado(AESUtil.decrypt(e.getPrimerApellidoEmpleado()));
			e.setSegundoApellidoEmpleado(AESUtil.decrypt(e.getSegundoApellidoEmpleado()));
			e.setSalario(Double.valueOf(AESUtil.decrypt(e.getSalario() + "")));
		
		
			 AdministradorDTO dto = new AdministradorDTO();
			 
	            dto.setIdEmpleado(u.getIdEmpleado());
	            dto.setNumeroCubiculo(Integer.parseInt(AESUtil.decrypt(u.getNumeroCubiculo().toString())));

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

	
	public int updateById(Integer id, AdministradorDTO newData, EmpleadoDTO empleado) {
		Optional<Administrador> opt = AdministradorRepo.findById(id);
		if (opt.isEmpty()) {
			return 2;
		}
		empleadoServ.updateById(id, empleado);
		Administrador admin = opt.get();

		admin.setEmpleado(modelMapper.map(empleado, Empleado.class));
		admin.setNumeroCubiculo(Integer.parseInt(AESUtil.encrypt(newData.getNumeroCubiculo().toString())));
		
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

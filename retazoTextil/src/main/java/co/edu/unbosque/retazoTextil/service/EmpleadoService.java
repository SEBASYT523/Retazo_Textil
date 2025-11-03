package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.EmpleadoDTO;
import co.edu.unbosque.retazoTextil.model.Empleado;
import co.edu.unbosque.retazoTextil.repository.EmpleadoRepository;
import co.edu.unbosque.retazoTextil.util.AESUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepo;

	@Autowired
	private ModelMapper modelMapper;

	public EmpleadoService(EmpleadoRepository empleadoRepo, ModelMapper modelMapper) {
		this.empleadoRepo = empleadoRepo;
		this.modelMapper = modelMapper;
	}

	
	public long count() {
		return empleadoRepo.count();
	}

	public boolean exist(Integer id) {
		return empleadoRepo.existsById(id);
	}

	
	public Empleado create(EmpleadoDTO data) {
		if (findIdAlreadyTaken(data.getIdEmpleado())) {
			return null;
		}

		Empleado entity = modelMapper.map(data, Empleado.class);
		entity.setIdEmpleado(data.getIdEmpleado());
		entity.setContrasenia(AESUtil.hashinBCrypt(data.getContrasenia()));
		entity.setDireccionEmpleado((data.getDireccionEmpleado()));
		entity.setTelefonoEmpleado((data.getTelefonoEmpleado()));
		entity.setPrimerNombreEmpleado((data.getPrimerNombreEmpleado()));
		entity.setSegundoNombreEmpleado((data.getSegundoNombreEmpleado()));
		entity.setPrimerApellidoEmpleado((data.getPrimerApellidoEmpleado()));
		entity.setSegundoApellidoEmpleado((data.getSegundoApellidoEmpleado()));

		if (data.getSalario() != null) {
			entity.setSalario(data.getSalario().doubleValue());
		}

		empleadoRepo.save(entity);
		return entity;
	}

	
	public List<EmpleadoDTO> getAll() {
		List<Empleado> entities = empleadoRepo.findAll();
		List<EmpleadoDTO> dtos = new ArrayList<>();

		for (Empleado u : entities) {

			EmpleadoDTO dto = modelMapper.map(u, EmpleadoDTO.class);
			dtos.add(dto);
		}
		return dtos;
	}

	
	public int deleteById(Integer id) {
		return empleadoRepo.findById(id).map(u -> {
			empleadoRepo.delete(u);
			return 0;
		}).orElse(1);
	}

	
	public int updateById(Integer id, EmpleadoDTO newData) {
		Optional<Empleado> opt = empleadoRepo.findById(id);
		if (opt.isEmpty()) {
			return 2;
		}

		Empleado empleado = opt.get();

		empleado.setPrimerNombreEmpleado((newData.getPrimerNombreEmpleado()));
		empleado.setSegundoNombreEmpleado((newData.getSegundoNombreEmpleado()));
		empleado.setPrimerApellidoEmpleado((newData.getPrimerApellidoEmpleado()));
		empleado.setSegundoApellidoEmpleado((newData.getSegundoApellidoEmpleado()));
		empleado.setTelefonoEmpleado((newData.getTelefonoEmpleado()));
		empleado.setDireccionEmpleado((newData.getDireccionEmpleado()));

		if (newData.getContrasenia() != null && !newData.getContrasenia().isBlank()) {
			empleado.setContrasenia(AESUtil.hashinBCrypt(newData.getContrasenia()));
		}

		if (newData.getSalario() != null) {
			empleado.setSalario(newData.getSalario().doubleValue());
		}

		empleado.setPaisNacimiento(newData.getPaisNacimiento());
		empleado.setCiudadNacimiento(newData.getCiudadNacimiento());
		empleado.setFechaNacimiento(newData.getFechaNacimiento());
		empleado.setFechaIngreso(newData.getFechaIngreso());

		empleadoRepo.save(empleado);
		return 0;
	}

	public EmpleadoDTO getById(Integer id) {
		return empleadoRepo.findById(id).map(u -> {

			return modelMapper.map(u, EmpleadoDTO.class);
		}).orElse(null);

	}

	public boolean findIdAlreadyTaken(Integer id) {
		return empleadoRepo.findById(id).isPresent();
	}

	public EmpleadoDTO validateCredentials(Integer id, String password) {
		Optional<Empleado> opt = empleadoRepo.findById(id);
		if (opt.isPresent() && AESUtil.validatePassword(password, opt.get().getContrasenia())) {
			EmpleadoDTO dto = modelMapper.map(opt.get(), EmpleadoDTO.class);
			dto.setIdEmpleado(id);
			return dto;
		}
		return null ;
	}
	
	public Empleado saveEntity(Empleado empleado) {
	    return empleadoRepo.save(empleado);
	}
	
	public Empleado getEntityById(Integer id) {
	    return empleadoRepo.findById(id).orElse(null);
	}

}

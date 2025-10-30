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
public class EmpleadoService implements CRUDOperation<EmpleadoDTO> {
	@Autowired
	private EmpleadoRepository empleadoRepo;
	@Autowired
	private ModelMapper modelMapper;

	public EmpleadoService(EmpleadoRepository empleadoRepo, ModelMapper modelMapper) {
		this.empleadoRepo = empleadoRepo;
		this.modelMapper = modelMapper;

	}

	@Override
	public long count() {
		return empleadoRepo.count();
	}

	@Override
	public boolean exist(Integer id) {
		return empleadoRepo.existsById(id);
	}

	@Override
	public int create(EmpleadoDTO data) {
		if (findIdAlreadyTaken(data.getIdEmpleado())) {
			return 1;
		}
		Empleado entity = new Empleado();

		entity = modelMapper.map(data, Empleado.class);
		entity.setIdEmpleado(null);
		entity.setContrasenia(AESUtil.hashinBCrypt(data.getContrasenia()));
		entity.setDireccionEmpleado(AESUtil.encrypt(data.getDireccionEmpleado()));
		entity.setTelefonoEmpleado(AESUtil.encrypt(data.getTelefonoEmpleado()));
		entity.setPrimerNombreEmpleado(AESUtil.encrypt(data.getPrimerNombreEmpleado()));
		entity.setSegundoNombreEmpleado(AESUtil.encrypt(data.getSegundoNombreEmpleado()));
		entity.setPrimerApellidoEmpleado(AESUtil.encrypt(data.getPrimerApellidoEmpleado()));
		entity.setSegundoApellidoEmpleado(AESUtil.encrypt(data.getSegundoApellidoEmpleado()));
		entity.setSalario(Double.valueOf(AESUtil.encrypt(data.getSalario() + "")));
		empleadoRepo.save(entity);
		return 0;
	}

	@Override
	public List<EmpleadoDTO> getAll() {
		List<Empleado> entities = empleadoRepo.findAll();
		List<EmpleadoDTO> dtos = new ArrayList<>();
		for (Empleado u : entities) {
			u.setDireccionEmpleado(AESUtil.decrypt(u.getDireccionEmpleado()));
			u.setTelefonoEmpleado(AESUtil.decrypt(u.getTelefonoEmpleado()));
			u.setPrimerNombreEmpleado(AESUtil.decrypt(u.getPrimerNombreEmpleado()));
			u.setSegundoNombreEmpleado(AESUtil.decrypt(u.getSegundoNombreEmpleado()));
			u.setPrimerApellidoEmpleado(AESUtil.decrypt(u.getPrimerApellidoEmpleado()));
			u.setSegundoApellidoEmpleado(AESUtil.decrypt(u.getSegundoApellidoEmpleado()));
			u.setSalario(Double.valueOf(AESUtil.decrypt(u.getSalario() + "")));
			EmpleadoDTO dto = modelMapper.map(u, EmpleadoDTO.class);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public int deleteById(Integer id) {
		return empleadoRepo.findById(id).map(u -> {
			empleadoRepo.delete(u);
			return 0;
		}).orElse(1);
	}

	@Override
	public int updateById(Integer id, EmpleadoDTO newData) {
		Optional<Empleado> opt = empleadoRepo.findById(id);
		if (opt.isEmpty()) {
			return 2;
		}

		Empleado empleado = opt.get();

		empleado.setPrimerNombreEmpleado(AESUtil.encrypt(newData.getPrimerNombreEmpleado()));
		empleado.setSegundoNombreEmpleado(AESUtil.encrypt(newData.getSegundoNombreEmpleado()));
		empleado.setPrimerApellidoEmpleado(AESUtil.encrypt(newData.getPrimerApellidoEmpleado()));
		empleado.setSegundoApellidoEmpleado(AESUtil.encrypt(newData.getSegundoApellidoEmpleado()));
		empleado.setTelefonoEmpleado(AESUtil.encrypt(newData.getTelefonoEmpleado()));
		empleado.setDireccionEmpleado(AESUtil.encrypt(newData.getDireccionEmpleado()));

		if (newData.getContrasenia() != null && !newData.getContrasenia().isBlank()) {
			empleado.setContrasenia(AESUtil.hashinBCrypt(newData.getContrasenia()));
		}

		if (newData.getSalario() != null) {
			empleado.setSalario(Double.valueOf(AESUtil.encrypt(newData.getSalario().toString())));
		}

		empleado.setPaisNacimiento(AESUtil.encrypt(newData.getPaisNacimiento()));
		empleado.setCiudadNacimiento(AESUtil.encrypt(newData.getCiudadNacimiento()));
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

	public int validateCredentials(Integer id, String password) {

		Optional<Empleado> opt = empleadoRepo.findById(id);
		System.out.println(opt);
		System.out.println(opt.get().getContrasenia());
		System.out.println(password);
		if (opt.isPresent() && AESUtil.validatePassword(password, opt.get().getContrasenia())) {
			return 0;
		}
		return 1;
	}

}

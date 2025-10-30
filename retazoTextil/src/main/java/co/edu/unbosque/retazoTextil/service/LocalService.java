package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.LocalDTO;
import co.edu.unbosque.retazoTextil.model.Local;
import co.edu.unbosque.retazoTextil.repository.LocalRepository;
import co.edu.unbosque.retazoTextil.util.AESUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocalService {

	@Autowired
	private LocalRepository localRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(LocalDTO data) {

		Local local = new Local();

		local.setDireccion(AESUtil.encrypt(data.getDireccion()));
		local.setTelefono(Integer.valueOf(AESUtil.encrypt(data.getTelefono().toString())));

		localRepo.save(local);
		return 0;

	}

	public List<LocalDTO> getAll() {
		List<Local> entities = localRepo.findAll();
		List<LocalDTO> dtos = new ArrayList<>();

		for (Local l : entities) {
			LocalDTO dto = modelMapper.map(l, LocalDTO.class);

			dto.setDireccion(AESUtil.decrypt(l.getDireccion()));
			dto.setTelefono(Integer.valueOf(AESUtil.decrypt(l.getTelefono().toString())));

			dtos.add(dto);
		}

		return dtos;
	}

	public LocalDTO getById(Integer id) {
		Optional<Local> opt = localRepo.findById(id);
		if (opt.isEmpty()) {
			return null;
		}

		Local l = opt.get();
		LocalDTO dto = modelMapper.map(l, LocalDTO.class);

		dto.setDireccion(AESUtil.decrypt(l.getDireccion()));
		dto.setTelefono(Integer.valueOf(AESUtil.decrypt(l.getTelefono().toString())));

		return dto;
	}

	public int updateById(Integer id, LocalDTO newData) {
		Optional<Local> opt = localRepo.findById(id);
		if (opt.isEmpty()) {
			return 1;
		}

		Local local = opt.get();

		if (newData.getDireccion() != null)
			local.setDireccion(AESUtil.encrypt(newData.getDireccion()));

		if (newData.getTelefono() != null)
			local.setTelefono(Integer.valueOf(AESUtil.encrypt(newData.getTelefono().toString())));

		localRepo.save(local);
		return 0;

	}

	public int deleteById(Integer id) {
		Optional<Local> opt = localRepo.findById(id);
		if (opt.isEmpty()) {
			return 1;
		}
		localRepo.delete(opt.get());
		return 0;
	}

	public boolean exist(Integer id) {
		return localRepo.existsById(id);
	}

	public long count() {
		return localRepo.count();
	}
}

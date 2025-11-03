package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.BodegaDTO;
import co.edu.unbosque.retazoTextil.model.Bodega;
import co.edu.unbosque.retazoTextil.model.Local;
import co.edu.unbosque.retazoTextil.repository.BodegaRepository;
import co.edu.unbosque.retazoTextil.repository.LocalRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BodegaService {

	@Autowired
	private BodegaRepository bodegaRepo;

	@Autowired
	private LocalRepository localRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(BodegaDTO data) {
		Optional<Local> optLocal = localRepo.findById(data.getLocalId());
		if (optLocal.isEmpty()) {
			return 1;
		}

		Bodega bodega = new Bodega();
		bodega.setFechaCompra(data.getFechaCompra());
		bodega.setCapacidad(Integer.parseInt((data.getCapacidad().toString())));
		bodega.setLocal(optLocal.get());

		bodegaRepo.save(bodega);
		return 0;
	}

	public List<BodegaDTO> getAll() {
		List<Bodega> entities = bodegaRepo.findAll();
		List<BodegaDTO> dtos = new ArrayList<>();

		for (Bodega b : entities) {
			BodegaDTO dto = modelMapper.map(b, BodegaDTO.class);
			dto.setLocalId(b.getLocal().getNumeroLocal());
			dtos.add(dto);
		}

		return dtos;
	}

	public BodegaDTO getById(Integer id) {
		Optional<Bodega> opt = bodegaRepo.findById(id);
		if (opt.isEmpty()) {
			return null;
		}

		Bodega b = opt.get();
		BodegaDTO dto = modelMapper.map(b, BodegaDTO.class);
		dto.setLocalId(b.getLocal().getNumeroLocal());
		return dto;
	}

	public int updateById(Integer id, BodegaDTO newData) {
		Optional<Bodega> opt = bodegaRepo.findById(id);
		if (opt.isEmpty()) {
			return 2;
		}
		
		Bodega bodega = opt.get();

		bodega.setFechaCompra(newData.getFechaCompra());
		bodega.setCapacidad(newData.getCapacidad());

		if (newData.getLocalId() != null) {
			Local local = localRepo.findById(newData.getLocalId()).orElse(null);
			bodega.setLocal(local);
		}

		bodegaRepo.save(bodega);
		return 0;
	}

	public int deleteById(Integer id) {
		Optional<Bodega> opt = bodegaRepo.findById(id);
		if (opt.isEmpty()) {
			return 1;
		}

		bodegaRepo.delete(opt.get());
		return 0;
	}

	public boolean findIdAlreadyTaken(Integer id) {
		return bodegaRepo.findById(id).isPresent();
	}

	public long count() {
		return bodegaRepo.count();
	}

	public boolean exist(Integer id) {
		return bodegaRepo.existsById(id);
	}
}

package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.AccederDTO;
import co.edu.unbosque.retazoTextil.model.Acceder;
import co.edu.unbosque.retazoTextil.model.AccederId;
import co.edu.unbosque.retazoTextil.model.Bodega;
import co.edu.unbosque.retazoTextil.model.Vendedor;
import co.edu.unbosque.retazoTextil.repository.AccederRepository;
import co.edu.unbosque.retazoTextil.repository.BodegaRepository;
import co.edu.unbosque.retazoTextil.repository.VendedorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccederService {

	@Autowired
	private AccederRepository accederRepository;
	@Autowired
	private BodegaRepository bodegaRepository;
	@Autowired
	private VendedorRepository vendedorRepository;

	public int create(AccederDTO dto) {
		Bodega bodega = bodegaRepository.findById(dto.getBodegaId()).orElse(null);
		if (bodega == null) {
			return 1;
		}

		Vendedor vendedor = vendedorRepository.findById(dto.getVendedorId()).orElse(null);
		if (vendedor == null) {
			return 2;
		}

		Acceder acceder = new Acceder();
		acceder.setId(new AccederId(dto.getBodegaId(), dto.getVendedorId()));
		acceder.setBodega(bodega);
		acceder.setVendedor(vendedor);

		accederRepository.save(acceder);

		return 0;
	}

	public List<AccederDTO> getAll() {
		List<Acceder> entidades = accederRepository.findAll();
		List<AccederDTO> dtos = new ArrayList<>();

		for (Acceder a : entidades) {
			Integer numeroBodega = a.getBodega().getNumeroBodega();
			Integer idVendedor = a.getVendedor().getIdEmpleado();

			AccederDTO dto = new AccederDTO(numeroBodega, idVendedor);
			dtos.add(dto);
		}

		return dtos;
	}

	public long count() {
		accederRepository.count();
		return 0;
	}

	public boolean exist(Integer idBodega, Integer vendedorId) {
		AccederId id = new AccederId(idBodega, vendedorId);
		return accederRepository.existsById(id);
	}

	public int deleteById(Integer idBodega, Integer vendedorId) {
		AccederId id = new AccederId(idBodega, vendedorId);
		return accederRepository.findById(id).map(u -> {
			accederRepository.delete(u);
			return 0;
		}).orElse(1);
	}

	public int updateById(AccederDTO dto) {

		AccederId id = new AccederId(dto.getBodegaId(), dto.getVendedorId());
		Optional<Acceder> opt = accederRepository.findById(id);

		if (opt.isEmpty()) {
			return 1;
		}

		Acceder acceder = opt.get();

		if (dto.getBodegaId() != null && !dto.getBodegaId().equals(acceder.getBodega().getNumeroBodega())) {
			Bodega nuevaBodega = bodegaRepository.findById(dto.getBodegaId()).orElse(null);
			if (nuevaBodega == null) {
				return 2;
			}
			acceder.setBodega(nuevaBodega);
		}

		if (dto.getVendedorId() != null && !dto.getVendedorId().equals(acceder.getVendedor().getIdEmpleado())) {
			Vendedor nuevoVendedor = vendedorRepository.findById(dto.getVendedorId()).orElse(null);
			if (nuevoVendedor == null) {
				return 3;
			}
			acceder.setVendedor(nuevoVendedor);
		}

		acceder.setId(new AccederId(acceder.getBodega().getNumeroBodega(), acceder.getVendedor().getIdEmpleado()));

		accederRepository.save(acceder);
		return 0;
	}

}

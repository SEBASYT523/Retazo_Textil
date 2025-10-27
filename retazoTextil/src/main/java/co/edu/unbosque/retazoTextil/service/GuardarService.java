package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.GuardarDTO;
import co.edu.unbosque.retazoTextil.model.Guardar;
import co.edu.unbosque.retazoTextil.model.GuardarId;
import co.edu.unbosque.retazoTextil.model.Bodega;
import co.edu.unbosque.retazoTextil.model.Producto;
import co.edu.unbosque.retazoTextil.repository.BodegaRepository;
import co.edu.unbosque.retazoTextil.repository.GuardarRepository;
import co.edu.unbosque.retazoTextil.repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardarService {

	@Autowired
	private GuardarRepository guardarRepository;
	@Autowired
	private BodegaRepository bodegaRepository;
	@Autowired
	private ProductoRepository productoRepo;

	public int create(GuardarDTO dto) {
		Bodega bodega = bodegaRepository.findById(dto.getBodegaId()).orElse(null);
		if (bodega == null) {
			return 1;
		}

		Producto producto = productoRepo.findById(dto.getProductoId()).orElse(null);
		if (producto == null) {
			return 2;
		}

		Guardar guardar = new Guardar();
		guardar.setId(new GuardarId(dto.getBodegaId(), dto.getProductoId()));
		guardar.setBodega(bodega);
		guardar.setProducto(producto);

		guardarRepository.save(guardar);

		return 0;
	}

	public List<GuardarDTO> getAll() {
		List<Guardar> entidades = guardarRepository.findAll();
		List<GuardarDTO> dtos = new ArrayList<>();

		for (Guardar a : entidades) {
			Integer numeroBodega = a.getBodega().getNumeroBodega();
			Integer idProducto = a.getProducto().getCodProducto();

			GuardarDTO dto = new GuardarDTO(numeroBodega, idProducto);
			dtos.add(dto);
		}

		return dtos;
	}

	public long count() {
		guardarRepository.count();
		return 0;
	}

	public boolean exist(Integer idBodega, Integer productoId) {
		GuardarId id = new GuardarId(idBodega, productoId);
		return guardarRepository.existsById(id);
	}

	public int deleteById(Integer idBodega, Integer productoId) {
		GuardarId id = new GuardarId(idBodega, productoId);
		return guardarRepository.findById(id).map(u -> {
			guardarRepository.delete(u);
			return 0;
		}).orElse(1);
	}

	public int updateById(GuardarDTO dto) {

		GuardarId id = new GuardarId(dto.getBodegaId(), dto.getProductoId());
		Optional<Guardar> opt = guardarRepository.findById(id);

		if (opt.isEmpty()) {
			return 1;
		}

		Guardar guardar = opt.get();

		if (dto.getBodegaId() != null && !dto.getBodegaId().equals(guardar.getBodega().getNumeroBodega())) {
			Bodega nuevaBodega = bodegaRepository.findById(dto.getBodegaId()).orElse(null);
			if (nuevaBodega == null) {
				return 2;
			}
			guardar.setBodega(nuevaBodega);
		}

		if (dto.getProductoId() != null && !dto.getProductoId().equals(guardar.getProducto().getCodProducto())) {
			Producto nuevoProducto = productoRepo.findById(dto.getProductoId()).orElse(null);
			if (nuevoProducto == null) {
				return 3;
			}
			guardar.setProducto(nuevoProducto);
		}

		guardar.setId(new GuardarId(guardar.getBodega().getNumeroBodega(), guardar.getProducto().getCodProducto()));

		guardarRepository.save(guardar);
		return 0;
	}

}

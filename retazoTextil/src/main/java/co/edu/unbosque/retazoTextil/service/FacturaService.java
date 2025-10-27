package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.FacturaDTO;
import co.edu.unbosque.retazoTextil.model.*;
import co.edu.unbosque.retazoTextil.repository.*;
import co.edu.unbosque.retazoTextil.util.AESUtil;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository facturaRepo;

	@Autowired
	private VentaRepository ventaRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(FacturaDTO data) {
		Factura factura = new Factura();
		factura.setMetodoPago(data.getMetodoPago());

		if (data.getVentaId() != null) {
			Optional<Venta> optVenta = ventaRepo.findById(data.getVentaId());
			if (optVenta.isEmpty()) {
				return 1;
			}
			factura.setVenta(optVenta.get());
		}
		
		factura.setMetodoPago(AESUtil.encrypt(data.getMetodoPago()));

		facturaRepo.save(factura);
		return 0;
	}

	public List<FacturaDTO> getAll() {
		List<Factura> entities = facturaRepo.findAll();
		List<FacturaDTO> dtos = new ArrayList<>();

		for (Factura f : entities) {
			FacturaDTO dto = modelMapper.map(f, FacturaDTO.class);
			if (f.getVenta() != null) {
				dto.setVentaId(f.getVenta().getCodigoVenta());
			}
			dto.setMetodoPago(AESUtil.encrypt(f.getMetodoPago()));
			dtos.add(dto);

		}

		return dtos;
	}

	public FacturaDTO getById(Integer id) {
		Optional<Factura> opt = facturaRepo.findById(id);
		if (opt.isEmpty()) {
			return null;
		}

		Factura f = opt.get();
		FacturaDTO dto = modelMapper.map(f, FacturaDTO.class);
		if (f.getVenta() != null) {
			dto.setVentaId(f.getVenta().getCodigoVenta());
		}

		return dto;
	}

	public int updateById(Integer id, FacturaDTO newData) {
		Optional<Factura> optFactura = facturaRepo.findById(id);
		if (optFactura.isEmpty()) {
			return 2;
		}

		Factura factura = optFactura.get();
		factura.setMetodoPago(newData.getMetodoPago());

		if (newData.getVentaId() != null) {
			Optional<Venta> optVenta = ventaRepo.findById(newData.getVentaId());
			if (optVenta.isEmpty()) {
				return 1;
			}
			factura.setVenta(optVenta.get());
		}

		factura.setMetodoPago(AESUtil.encrypt(newData.getMetodoPago()));
		facturaRepo.save(factura);
		return 0;
	}

	public int deleteById(Integer id) {
		Optional<Factura> opt = facturaRepo.findById(id);
		if (opt.isEmpty()) {
			return 1;
		}

		facturaRepo.delete(opt.get());
		return 0;
	}

	public boolean exist(Integer id) {
		return facturaRepo.existsById(id);
	}

	public long count() {
		return facturaRepo.count();
	}
}

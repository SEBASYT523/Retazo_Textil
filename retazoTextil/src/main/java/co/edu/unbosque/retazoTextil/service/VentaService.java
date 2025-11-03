package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.VentaDTO;
import co.edu.unbosque.retazoTextil.model.Empleado;
import co.edu.unbosque.retazoTextil.model.Factura;
import co.edu.unbosque.retazoTextil.model.Venta;
import co.edu.unbosque.retazoTextil.repository.EmpleadoRepository;
import co.edu.unbosque.retazoTextil.repository.FacturaRepository;
import co.edu.unbosque.retazoTextil.repository.VentaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepo;

	@Autowired
	private EmpleadoRepository empleadoRepo;

	@Autowired
	private FacturaRepository facturaRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(VentaDTO data) {
		Optional<Empleado> optEmpleado = empleadoRepo.findById(data.getEmpleadoId());
		if (optEmpleado.isEmpty()) {
			return 1;
		}

		Optional<Factura> optFactura = facturaRepo.findById(data.getFacturaId());
		if (optFactura.isEmpty()) {
			return 2;
		}

		Venta venta = new Venta();
		venta.setFechaVenta(data.getFechaVenta() != null ? data.getFechaVenta() : LocalDate.now());
		venta.setValorTotal(data.getValorTotal());
		venta.setEmpleado(optEmpleado.get());
		venta.setFactura(optFactura.get());

		ventaRepo.save(venta);
		return 0;
	}

	public List<VentaDTO> getAll() {
		List<Venta> entities = ventaRepo.findAll();
		List<VentaDTO> dtos = new ArrayList<>();

		for (Venta v : entities) {
			VentaDTO dto = modelMapper.map(v, VentaDTO.class);
			dto.setEmpleadoId(v.getEmpleado().getIdEmpleado());
			dto.setFacturaId(v.getFactura().getNumeroFactura());
			dtos.add(dto);
		}

		return dtos;
	}

	public VentaDTO getById(Integer id) {
		Optional<Venta> opt = ventaRepo.findById(id);
		if (opt.isEmpty()) {
			return null;
		}

		Venta v = opt.get();
		VentaDTO dto = modelMapper.map(v, VentaDTO.class);
		dto.setEmpleadoId(v.getEmpleado().getIdEmpleado());
		dto.setFacturaId(v.getFactura().getNumeroFactura());
		return dto;
	}

	public int updateById(Integer id, VentaDTO newData) {
		Optional<Venta> optVenta = ventaRepo.findById(id);
		if (optVenta.isEmpty()) {
			return 3;
		}

		Venta venta = optVenta.get();

		if (newData.getValorTotal() != null) {
			venta.setValorTotal(newData.getValorTotal());
		}

		if (newData.getFechaVenta() != null) {
			venta.setFechaVenta(newData.getFechaVenta());
		}

		if (newData.getEmpleadoId() != null) {
			Optional<Empleado> optEmpleado = empleadoRepo.findById(newData.getEmpleadoId());
			if (optEmpleado.isEmpty()) {
				return 1;
			}
			venta.setEmpleado(optEmpleado.get());
		}

		if (newData.getFacturaId() != null) {
			Optional<Factura> optFactura = facturaRepo.findById(newData.getFacturaId());
			if (optFactura.isEmpty()) {
				return 2;
			}
			venta.setFactura(optFactura.get());
		}

		ventaRepo.save(venta);
		return 0;
	}

	public int deleteById(Integer id) {
		Optional<Venta> opt = ventaRepo.findById(id);
		if (opt.isEmpty()) {
			return 1;
		}

		ventaRepo.delete(opt.get());
		return 0;
	}

	public boolean exists(Integer id) {
		return ventaRepo.existsById(id);
	}

	public long count() {
		return ventaRepo.count();
	}
}

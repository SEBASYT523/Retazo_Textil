package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.VentaDTO;
import co.edu.unbosque.retazoTextil.model.*;
import co.edu.unbosque.retazoTextil.repository.*;
import co.edu.unbosque.retazoTextil.util.AESUtil;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
        venta.setValorTotal(BigDecimal.valueOf(Double.valueOf(AESUtil.encrypt(data.getValorTotal().toString()))));
        venta.setFechaVenta(data.getFechaVenta() != null ? data.getFechaVenta() : LocalDate.now());
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
	        dto.setValorTotal(BigDecimal.valueOf(Double.valueOf(AESUtil.decrypt(dto.getValorTotal().toString()))));
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

		if (newData.getValorTotal() != null)
			venta.setValorTotal(newData.getValorTotal());

		if (newData.getFechaVenta() != null)
			venta.setFechaVenta(newData.getFechaVenta());

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
		
		venta.setValorTotal(BigDecimal.valueOf(Double.valueOf(AESUtil.encrypt(newData.getValorTotal().toString()))));

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

	public boolean exist(Integer id) {
		return ventaRepo.existsById(id);
	}

	public long count() {
		return ventaRepo.count();
	}
}

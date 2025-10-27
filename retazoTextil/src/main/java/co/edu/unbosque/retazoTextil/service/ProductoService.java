package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.ProductoDTO;
import co.edu.unbosque.retazoTextil.model.*;
import co.edu.unbosque.retazoTextil.repository.*;
import co.edu.unbosque.retazoTextil.util.AESUtil;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepo;

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private ProveedorRepository proveedorRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(ProductoDTO data) {
		Optional<Proveedor> optProveedor = proveedorRepo.findById(data.getProveedorId());
		if (optProveedor.isEmpty()) {
			return 1;
		}

		Producto producto = new Producto();
		producto.setNombre(data.getNombre());
		producto.setTipoProducto(data.getTipoProducto());
		producto.setColor(data.getColor());

		producto.setPrecio(BigDecimal.valueOf(Double.valueOf(AESUtil.encrypt(data.getPrecio().toString()))));

		if (data.getClienteId() != null) {
			Optional<Cliente> optCliente = clienteRepo.findById(data.getClienteId());
			optCliente.ifPresent(producto::setCliente);
		}

		producto.setProveedor(optProveedor.get());

		productoRepo.save(producto);
		return 0;
	}

	public List<ProductoDTO> getAll() {
		List<Producto> productos = productoRepo.findAll();
		List<ProductoDTO> dtos = new ArrayList<>();

		for (Producto p : productos) {
			ProductoDTO dto = modelMapper.map(p, ProductoDTO.class);

			try {
				dto.setPrecio(BigDecimal.valueOf(Double.valueOf(AESUtil.decrypt(p.getPrecio().toString()))));
			} catch (Exception e) {
				dto.setPrecio(BigDecimal.ZERO);
			}

			dto.setClienteId(p.getCliente() != null ? p.getCliente().getIdCliente() : null);
			dto.setProveedorId(p.getProveedor() != null ? p.getProveedor().getIdProveedor() : null);

			dtos.add(dto);
		}

		return dtos;
	}

	public ProductoDTO getById(Integer id) {
		Optional<Producto> opt = productoRepo.findById(id);
		if (opt.isEmpty()) {
			return null;
		}

		Producto p = opt.get();
		ProductoDTO dto = modelMapper.map(p, ProductoDTO.class);

		try {
			dto.setPrecio(BigDecimal.valueOf(Double.valueOf(AESUtil.decrypt(p.getPrecio().toString()))));
		} catch (Exception e) {
			dto.setPrecio(BigDecimal.ZERO);
		}

		dto.setClienteId(p.getCliente() != null ? p.getCliente().getIdCliente() : null);
		dto.setProveedorId(p.getProveedor() != null ? p.getProveedor().getIdProveedor() : null);

		return dto;
	}

	public int updateById(Integer id, ProductoDTO newData) {
		Optional<Producto> optProducto = productoRepo.findById(id);
		if (optProducto.isEmpty()) {
			return 2;
		}

		Producto producto = optProducto.get();

		if (newData.getNombre() != null)
			producto.setNombre(newData.getNombre());

		if (newData.getTipoProducto() != null)
			producto.setTipoProducto(newData.getTipoProducto());

		if (newData.getColor() != null)
			producto.setColor(newData.getColor());

		if (newData.getPrecio() != null)
			producto.setPrecio(BigDecimal.valueOf(Double.valueOf(AESUtil.encrypt(newData.getPrecio().toString()))));

		if (newData.getClienteId() != null) {
			Optional<Cliente> optCliente = clienteRepo.findById(newData.getClienteId());
			optCliente.ifPresent(producto::setCliente);
		}

		if (newData.getProveedorId() != null) {
			Optional<Proveedor> optProveedor = proveedorRepo.findById(newData.getProveedorId());
			if (optProveedor.isEmpty()) {
				return 1;
			}
			producto.setProveedor(optProveedor.get());
		}

		productoRepo.save(producto);
		return 0;
	}

	public int deleteById(Integer id) {
		Optional<Producto> opt = productoRepo.findById(id);
		if (opt.isEmpty()) {
			return 1;
		}

		productoRepo.delete(opt.get());
		return 0;
	}

	public boolean exist(Integer id) {
		return productoRepo.existsById(id);
	}

	public long count() {
		return productoRepo.count();
	}
}

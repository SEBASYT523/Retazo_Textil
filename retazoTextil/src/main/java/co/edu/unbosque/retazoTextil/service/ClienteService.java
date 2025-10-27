package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.ClienteDTO;
import co.edu.unbosque.retazoTextil.model.Cliente;
import co.edu.unbosque.retazoTextil.model.Producto;
import co.edu.unbosque.retazoTextil.repository.ClienteRepository;
import co.edu.unbosque.retazoTextil.repository.ProductoRepository;
import co.edu.unbosque.retazoTextil.util.AESUtil;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private ProductoRepository productoRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(ClienteDTO data) {
		Cliente cliente = new Cliente();

		cliente.setPrimerNombre(AESUtil.encrypt(data.getPrimerNombre()));
		cliente.setSegundoNombre(AESUtil.encrypt(data.getSegundoNombre()));
		cliente.setPrimerApellido(AESUtil.encrypt(data.getPrimerApellido()));
		cliente.setSegundoApellido(AESUtil.encrypt(data.getSegundoApellido()));
		cliente.setFechaNacimiento(data.getFechaNacimiento());
		cliente.setAnosComprando(data.getAnosComprando());

		cliente.setContrasenia(AESUtil.hashinBCrypt(data.getContrasenia()));
		cliente.setTelefono(AESUtil.encrypt(data.getTelefono()));
		cliente.setDireccion(AESUtil.encrypt(data.getDireccion()));

		if (data.getProductosId() != null && !data.getProductosId().isEmpty()) {
			List<Producto> productos = data.getProductosId().stream()
					.map(pid -> productoRepo.findById(pid).orElse(null)).filter(Objects::nonNull)
					.collect(Collectors.toList());
			cliente.setProductos(productos);
		}

		clienteRepo.save(cliente);
		return 0;
	}

	public List<ClienteDTO> getAll() {
		List<Cliente> entities = clienteRepo.findAll();
		List<ClienteDTO> dtos = new ArrayList<>();

		for (Cliente c : entities) {
			ClienteDTO dto = modelMapper.map(c, ClienteDTO.class);

			dto.setPrimerNombre(AESUtil.decrypt(dto.getPrimerNombre()));
			dto.setSegundoNombre(AESUtil.decrypt(dto.getSegundoNombre()));
			dto.setPrimerApellido(AESUtil.decrypt(dto.getPrimerApellido()));
			dto.setSegundoApellido(AESUtil.decrypt(dto.getSegundoApellido()));
			
			dto.setTelefono(AESUtil.decrypt(c.getTelefono()));
			dto.setDireccion(AESUtil.decrypt(c.getDireccion()));

			dto.setContrasenia(null);

			if (c.getProductos() != null) {
				dto.setProductosId(
						c.getProductos().stream().map(Producto::getCodProducto).collect(Collectors.toList()));
			}

			dtos.add(dto);
		}

		return dtos;
	}

	
	public ClienteDTO getById(Integer id) {
		Optional<Cliente> opt = clienteRepo.findById(id);
		if (opt.isEmpty()) {
			return null;
		}

		Cliente c = opt.get();
		ClienteDTO dto = modelMapper.map(c, ClienteDTO.class);

		dto.setTelefono(AESUtil.decrypt(c.getTelefono()));
		dto.setDireccion(AESUtil.decrypt(c.getDireccion()));
		dto.setContrasenia(null);

		if (c.getProductos() != null) {
			dto.setProductosId(c.getProductos().stream().map(Producto::getCodProducto).collect(Collectors.toList()));
		}

		return dto;
	}

	
	public int updateById(Integer id, ClienteDTO newData) {
		Optional<Cliente> optCliente = clienteRepo.findById(id);
		if (optCliente.isEmpty()) {
			return 1; 
		}

		Cliente cliente = optCliente.get();

		if (newData.getPrimerNombre() != null)
			cliente.setPrimerNombre(AESUtil.encrypt(newData.getPrimerNombre()));
		if (newData.getSegundoNombre() != null)
			cliente.setSegundoNombre(AESUtil.encrypt(newData.getSegundoNombre()));
		if (newData.getPrimerApellido() != null)
			cliente.setPrimerApellido(AESUtil.encrypt(newData.getPrimerApellido()));
		if (newData.getSegundoApellido() != null)
			cliente.setSegundoApellido(AESUtil.encrypt(newData.getSegundoApellido()));
		if (newData.getFechaNacimiento() != null)
			cliente.setFechaNacimiento(newData.getFechaNacimiento());
		if (newData.getAnosComprando() != null)
			cliente.setAnosComprando(newData.getAnosComprando());

		if (newData.getContrasenia() != null)
			cliente.setContrasenia(AESUtil.hashinBCrypt(newData.getContrasenia()));

		if (newData.getTelefono() != null)
			cliente.setTelefono(AESUtil.encrypt(newData.getTelefono()));

		if (newData.getDireccion() != null)
			cliente.setDireccion(AESUtil.encrypt(newData.getDireccion()));

		if (newData.getProductosId() != null && !newData.getProductosId().isEmpty()) {
			List<Producto> productos = newData.getProductosId().stream()
					.map(pid -> productoRepo.findById(pid).orElse(null)).filter(Objects::nonNull)
					.collect(Collectors.toList());
			cliente.setProductos(productos);
		}

		clienteRepo.save(cliente);
		return 0;
	}

	
	public int deleteById(Integer id) {
		Optional<Cliente> opt = clienteRepo.findById(id);
		if (opt.isEmpty()) {
			return 1;
		}

		clienteRepo.delete(opt.get());
		return 0;
	}

	
	public boolean exist(Integer id) {
		return clienteRepo.existsById(id);
	}

	public long count() {
		return clienteRepo.count();
	}
}

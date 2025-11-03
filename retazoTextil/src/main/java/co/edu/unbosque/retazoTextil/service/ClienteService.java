package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.ClienteDTO;
import co.edu.unbosque.retazoTextil.model.Cliente;
import co.edu.unbosque.retazoTextil.model.Pedido;
import co.edu.unbosque.retazoTextil.repository.ClienteRepository;
import co.edu.unbosque.retazoTextil.repository.PedidoRepository;
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
	private PedidoRepository pedidoRepo;

	@Autowired
	private ModelMapper modelMapper;

	// ✅ Crear cliente
	public int create(ClienteDTO data) {
		Cliente cliente = new Cliente();

		cliente.setPrimerNombre(data.getPrimerNombre());
		cliente.setSegundoNombre(data.getSegundoNombre());
		cliente.setPrimerApellido(data.getPrimerApellido());
		cliente.setSegundoApellido(data.getSegundoApellido());
		cliente.setFechaNacimiento(data.getFechaNacimiento());
		cliente.setContrasenia(AESUtil.hashinBCrypt(data.getContrasenia()));
		cliente.setTelefono(data.getTelefono());
		cliente.setDireccion(data.getDireccion());

		if (data.getpedidosId() != null && !data.getpedidosId().isEmpty()) {
			List<Pedido> pedidos = data.getpedidosId().stream().map(pid -> pedidoRepo.findById(pid).orElse(null))
					.filter(Objects::nonNull).collect(Collectors.toList());
			cliente.setPedidos(pedidos);
		}

		clienteRepo.save(cliente);
		return 0;
	}

	// ✅ Listar todos
	public List<ClienteDTO> getAll() {
		return clienteRepo.findAll().stream().map(c -> {
			ClienteDTO dto = modelMapper.map(c, ClienteDTO.class);
			dto.setContrasenia(null); // No mostrar contraseñas
			if (c.getPedidos() != null) {
				dto.setpedidosId(c.getPedidos().stream().map(Pedido::getId).collect(Collectors.toList()));
			}
			return dto;
		}).collect(Collectors.toList());
	}

	// ✅ Obtener por ID
	public ClienteDTO getById(Integer id) {
		return clienteRepo.findById(id).map(c -> {
			ClienteDTO dto = modelMapper.map(c, ClienteDTO.class);
			dto.setContrasenia(null);
			if (c.getPedidos() != null) {
				dto.setpedidosId(c.getPedidos().stream().map(Pedido::getId).collect(Collectors.toList()));
			}
			return dto;
		}).orElse(null);
	}

	// ✅ Actualizar
	public int updateById(Integer id, ClienteDTO newData) {
		Optional<Cliente> optCliente = clienteRepo.findById(id);
		if (optCliente.isEmpty())
			return 1;

		Cliente cliente = optCliente.get();

		if (newData.getPrimerNombre() != null)
			cliente.setPrimerNombre(newData.getPrimerNombre());
		if (newData.getSegundoNombre() != null)
			cliente.setSegundoNombre(newData.getSegundoNombre());
		if (newData.getPrimerApellido() != null)
			cliente.setPrimerApellido(newData.getPrimerApellido());
		if (newData.getSegundoApellido() != null)
			cliente.setSegundoApellido(newData.getSegundoApellido());
		if (newData.getFechaNacimiento() != null)
			cliente.setFechaNacimiento(newData.getFechaNacimiento());
		if (newData.getContrasenia() != null)
			cliente.setContrasenia(AESUtil.hashinBCrypt(newData.getContrasenia()));
		if (newData.getTelefono() != null)
			cliente.setTelefono(newData.getTelefono());
		if (newData.getDireccion() != null)
			cliente.setDireccion(newData.getDireccion());

		if (newData.getpedidosId() != null) {
			List<Pedido> pedidos = newData.getpedidosId().stream().map(pid -> pedidoRepo.findById(pid).orElse(null))
					.filter(Objects::nonNull).collect(Collectors.toList());
			cliente.setPedidos(pedidos);
		}

		clienteRepo.save(cliente);
		return 0;
	}

	public Cliente validateCredentials(Integer id, String password) {
		Optional<Cliente> opt = clienteRepo.findById(id);
		if (opt.isPresent() && AESUtil.validatePassword(password, opt.get().getContrasenia())) {
			return opt.get();
		}
		return null;
	}

	public int deleteById(Integer id) {
		return clienteRepo.findById(id).map(c -> {
			clienteRepo.delete(c);
			return 0;
		}).orElse(1);
	}

	public boolean exist(Integer id) {
		return clienteRepo.existsById(id);
	}

	public long count() {
		return clienteRepo.count();
	}
}

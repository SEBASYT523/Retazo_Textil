package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.ContactarDTO;
import co.edu.unbosque.retazoTextil.model.Contactar;
import co.edu.unbosque.retazoTextil.model.ContactarId;
import co.edu.unbosque.retazoTextil.model.Proveedor;
import co.edu.unbosque.retazoTextil.model.Administrador;
import co.edu.unbosque.retazoTextil.repository.ContactarRepository;
import co.edu.unbosque.retazoTextil.repository.ProveedorRepository;
import co.edu.unbosque.retazoTextil.repository.AdministradorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactarService {

	@Autowired
	private ContactarRepository contactarRepository;
	@Autowired
	private ProveedorRepository proveedorRepository;
	@Autowired
	private AdministradorRepository administradorRepository;

	public int create(ContactarDTO dto) {
		Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId()).orElse(null);
		if (proveedor == null) {
			return 1;
		}

		Administrador administrador = administradorRepository.findById(dto.getAdministradorId()).orElse(null);
		if (administrador == null) {
			return 2;
		}

		Contactar contactar = new Contactar();
		contactar.setId(new ContactarId(dto.getProveedorId(), dto.getAdministradorId()));
		contactar.setProveedor(proveedor);
		contactar.setAdministrador(administrador);

		contactarRepository.save(contactar);

		return 0;
	}

	public List<ContactarDTO> getAll() {
		List<Contactar> entidades = contactarRepository.findAll();
		List<ContactarDTO> dtos = new ArrayList<>();

		for (Contactar a : entidades) {
			Integer numeroProveedor = a.getProveedor().getIdProveedor();
			Integer idAdministrador = a.getAdministrador().getIdEmpleado();

			ContactarDTO dto = new ContactarDTO(a.getFechaSolicitud(), a.getFechaEntrega(), numeroProveedor, idAdministrador);
			dtos.add(dto);
		}

		return dtos;
	}

	public long count() {
		contactarRepository.count();
		return 0;
	}

	public boolean exist(Integer idProveedor, Integer administradorId) {
		ContactarId id = new ContactarId(idProveedor, administradorId);
		return contactarRepository.existsById(id);
	}

	public int deleteById(Integer idProveedor, Integer administradorId) {
		ContactarId id = new ContactarId(idProveedor, administradorId);
		return contactarRepository.findById(id).map(u -> {
			contactarRepository.delete(u);
			return 0;
		}).orElse(1);
	}

	public int updateById(ContactarDTO dto) {

		ContactarId id = new ContactarId(dto.getProveedorId(), dto.getAdministradorId());
		Optional<Contactar> opt = contactarRepository.findById(id);

		if (opt.isEmpty()) {
			return 1;
		}

		Contactar contactar = opt.get();

		if (dto.getProveedorId() != null && !dto.getProveedorId().equals(contactar.getProveedor().getIdProveedor())) {
			Proveedor nuevaProveedor = proveedorRepository.findById(dto.getProveedorId()).orElse(null);
			if (nuevaProveedor == null) {
				return 2;
			}
			contactar.setProveedor(nuevaProveedor);
		}

		if (dto.getAdministradorId() != null && !dto.getAdministradorId().equals(contactar.getAdministrador().getIdEmpleado())) {
			Administrador nuevoAdministrador = administradorRepository.findById(dto.getAdministradorId()).orElse(null);
			if (nuevoAdministrador == null) {
				return 3;
			}
			contactar.setAdministrador(nuevoAdministrador);
		}

		contactar.setId(new ContactarId(contactar.getProveedor().getIdProveedor(), contactar.getAdministrador().getIdEmpleado()));

		contactarRepository.save(contactar);
		return 0;
	}

}

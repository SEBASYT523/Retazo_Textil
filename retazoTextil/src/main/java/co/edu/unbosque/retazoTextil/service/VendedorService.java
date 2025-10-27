package co.edu.unbosque.retazoTextil.service;

import co.edu.unbosque.retazoTextil.dto.VendedorDTO;
import co.edu.unbosque.retazoTextil.dto.EmpleadoDTO;
import co.edu.unbosque.retazoTextil.model.Vendedor;

import co.edu.unbosque.retazoTextil.model.Empleado;
import co.edu.unbosque.retazoTextil.model.Local;
import co.edu.unbosque.retazoTextil.repository.LocalRepository;
import co.edu.unbosque.retazoTextil.repository.VendedorRepository;
import co.edu.unbosque.retazoTextil.util.AESUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {
	@Autowired
	private VendedorRepository vendedorRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LocalRepository localRepo;

	@Autowired
	private EmpleadoService empleadoServ;

	public VendedorService(VendedorRepository vendedorRepo, ModelMapper modelMapper) {
		this.vendedorRepo = vendedorRepo;
		this.modelMapper = modelMapper;

	}

	public long count() {
		return vendedorRepo.count();
	}

	public boolean exist(Integer id) {
		return vendedorRepo.existsById(id);
	}

	public int create(VendedorDTO data, EmpleadoDTO empleado) {
		if (findIdAlreadyTaken(data.getIdEmpleado())) {
			return 1;
		}
		empleadoServ.create(empleado);
		Vendedor entity = new Vendedor();

		Local local = localRepo.findById(data.getLocalId()).orElse(null);

		if (local == null) {
			return 1;
		}

		entity.setEmpleado(modelMapper.map(empleado, Empleado.class));
		entity.setNumeroProductosVendidos(
				Integer.parseInt(AESUtil.encrypt(data.getNumeroProductosVendidos().toString())));
		entity.setTotalVentas(Integer.parseInt(AESUtil.encrypt(data.getTotalVentas().toString())));
		entity.setLocal(local);

		vendedorRepo.save(entity);
		return 0;
	}

	public List<VendedorDTO> getAll() {
		List<Vendedor> entities = vendedorRepo.findAll();
		List<VendedorDTO> dtos = new ArrayList<>();
		for (Vendedor u : entities) {
			Empleado e = u.getEmpleado();

			e.setDireccionEmpleado(AESUtil.decrypt(e.getDireccionEmpleado()));
			e.setTelefonoEmpleado(AESUtil.decrypt(e.getTelefonoEmpleado()));
			e.setPrimerNombreEmpleado(AESUtil.decrypt(e.getPrimerNombreEmpleado()));
			e.setSegundoNombreEmpleado(AESUtil.decrypt(e.getSegundoNombreEmpleado()));
			e.setPrimerApellidoEmpleado(AESUtil.decrypt(e.getPrimerApellidoEmpleado()));
			e.setSegundoApellidoEmpleado(AESUtil.decrypt(e.getSegundoApellidoEmpleado()));
			e.setSalario(Double.valueOf(AESUtil.decrypt(e.getSalario() + "")));

			VendedorDTO dto = modelMapper.map(u, VendedorDTO.class);
			dto.setIdEmpleado(u.getIdEmpleado());
			dto.setNumeroProductosVendidos(
					Integer.parseInt(AESUtil.decrypt(dto.getNumeroProductosVendidos().toString())));
			dto.setTotalVentas(Integer.parseInt(AESUtil.decrypt(dto.getTotalVentas().toString())));

			dto.setLocalId(u.getLocal().getNumeroLocal());

			dtos.add(dto);
		}
		return dtos;
	}

	public int deleteById(Integer id) {
		return vendedorRepo.findById(id).map(u -> {
			vendedorRepo.delete(u);
			return 0;
		}).orElse(1);
	}

	public int updateById(Integer id, VendedorDTO newData, EmpleadoDTO empleadoData) {
        Optional<Vendedor> opt = vendedorRepo.findById(id);
        if (opt.isEmpty()) {
            return 2;
        }

        Vendedor vendedor = opt.get();
        Empleado empleado = vendedor.getEmpleado();

        empleadoServ.updateById(empleado.getIdEmpleado(), empleadoData);

        vendedor.setNumeroProductosVendidos(
				Integer.parseInt(AESUtil.encrypt(newData.getNumeroProductosVendidos().toString())));
		vendedor.setTotalVentas(Integer.parseInt(AESUtil.encrypt(newData.getTotalVentas().toString())));

        if (newData.getLocalId() != null) {
            Local local = localRepo.findById(newData.getLocalId())
                    .orElseThrow(() -> new RuntimeException("Local no encontrado"));
            vendedor.setLocal(local);
        }

        vendedorRepo.save(vendedor);
        return 0;
    }


	public VendedorDTO getById(Integer id) {
		return vendedorRepo.findById(id).map(u -> {

			return modelMapper.map(u, VendedorDTO.class);
		}).orElse(null);
	}

	public boolean findIdAlreadyTaken(Integer id) {
		return vendedorRepo.findById(id).isPresent();
	}

}

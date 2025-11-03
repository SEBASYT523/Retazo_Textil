package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.List;


public class AdministradorDTO implements Serializable{

	private Integer idEmpleado;

	private Integer numeroCubiculo;

	private List<Integer> proveedoresId;

	private List<ContactarId> contactosId;

	public AdministradorDTO() {
	}

	public AdministradorDTO(Integer numeroCubiculo) {
		this.numeroCubiculo = numeroCubiculo;

	}

	public AdministradorDTO(Integer numeroCubiculo,
			List<Integer> proveedoresId, List<ContactarId> contactosId) {
		super();
		this.numeroCubiculo = numeroCubiculo;

		this.proveedoresId = proveedoresId;
		this.contactosId = contactosId;
	}

	public Integer getNumeroCubiculo() {
		return numeroCubiculo;
	}

	public void setNumeroCubiculo(Integer numeroCubiculo) {
		this.numeroCubiculo = numeroCubiculo;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public List<Integer> getProveedoresId() {
		return proveedoresId;
	}

	public void setProveedoresId(List<Integer> proveedoresId) {
		this.proveedoresId = proveedoresId;
	}

	public List<ContactarId> getContactosId() {
		return contactosId;
	}

	public void setContactosId(List<ContactarId> contactosId) {
		this.contactosId = contactosId;
	}

}

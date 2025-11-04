package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.List;


public class ProveedorDTO implements Serializable{

	private Integer idProveedor;

	private String primerNombreProveedor;

	private String segundoNombreProveedor;

	private String primerApellidoProveedor;

	private String segundoApellidoProveedor;

	private String telefono;

	private String ciudad;

	private String pais;

	private Integer administradorId;

	private List<Integer> productosId;

	private List<ContactarId> contactosId;

	public ProveedorDTO() {
	}

	public ProveedorDTO(String primerNombreProveedor, String primerApellidoProveedor, String telefono, String pais,
			Integer administradorId) {
		this.primerNombreProveedor = primerNombreProveedor;
		this.primerApellidoProveedor = primerApellidoProveedor;
		this.telefono = telefono;
		this.pais = pais;
		this.administradorId = administradorId;
	}

	public ProveedorDTO(String primerNombreProveedor, String segundoNombreProveedor, String primerApellidoProveedor,
			String segundoApellidoProveedor, String telefono, String ciudad, String pais, Integer administradorId,
			List<Integer> productosId, List<ContactarId> contactosId) {
		super();
		this.primerNombreProveedor = primerNombreProveedor;
		this.segundoNombreProveedor = segundoNombreProveedor;
		this.primerApellidoProveedor = primerApellidoProveedor;
		this.segundoApellidoProveedor = segundoApellidoProveedor;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.pais = pais;
		this.administradorId = administradorId;
		this.productosId = productosId;
		this.contactosId = contactosId;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getPrimerNombreProveedor() {
		return primerNombreProveedor;
	}

	public void setPrimerNombreProveedor(String primerNombreProveedor) {
		this.primerNombreProveedor = primerNombreProveedor;
	}

	public String getSegundoNombreProveedor() {
		return segundoNombreProveedor;
	}

	public void setSegundoNombreProveedor(String segundoNombreProveedor) {
		this.segundoNombreProveedor = segundoNombreProveedor;
	}

	public String getPrimerApellidoProveedor() {
		return primerApellidoProveedor;
	}

	public void setPrimerApellidoProveedor(String primerApellidoProveedor) {
		this.primerApellidoProveedor = primerApellidoProveedor;
	}

	public String getSegundoApellidoProveedor() {
		return segundoApellidoProveedor;
	}

	public void setSegundoApellidoProveedor(String segundoApellidoProveedor) {
		this.segundoApellidoProveedor = segundoApellidoProveedor;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Integer getInteger() {
		return administradorId;
	}

	public void setInteger(Integer administradorId) {
		this.administradorId = administradorId;
	}

	public List<Integer> getIntegers() {
		return productosId;
	}

	public void setIntegers(List<Integer> productosId) {
		this.productosId = productosId;
	}

	public List<ContactarId> getContactos() {
		return contactosId;
	}

	public void setContactos(List<ContactarId> contactosId) {
		this.contactosId = contactosId;
	}

	public Integer getAdministradorId() {
		return administradorId;
	}

	public void setAdministradorId(Integer administradorId) {
		this.administradorId = administradorId;
	}
	
	

}

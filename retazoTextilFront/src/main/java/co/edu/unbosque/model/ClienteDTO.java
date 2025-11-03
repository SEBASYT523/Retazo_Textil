package co.edu.unbosque.model;

import java.time.LocalDate;
import java.util.List;

public class ClienteDTO {

	private Integer idCliente;

	private String primerNombre;

	private String segundoNombre;

	private String primerApellido;

	private String segundoApellido;

	private String contrasenia;

	private String telefono;

	private String direccion;

	private LocalDate fechaNacimiento;

	private List<PedidoId> pedidosId;

	public ClienteDTO() {
	}

	public ClienteDTO(Integer idCliente,String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
			String contrasenia, String telefono, String direccion, LocalDate fechaNacimiento) {
		this.idCliente = idCliente;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.contrasenia = contrasenia;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;

	}

	public ClienteDTO(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
			String contrasenia, String telefono, String direccion, LocalDate fechaNacimiento,
			List<PedidoId> pedidosId) {
		super();
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.contrasenia = contrasenia;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;

		this.pedidosId = pedidosId;
	}

	public List<PedidoId> getpedidosId() {
		return pedidosId;
	}

	public void setpedidosId(List<PedidoId> pedidosId) {
		this.pedidosId = pedidosId;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<PedidoId> getIntegers() {
		return pedidosId;
	}

	public void setIntegers(List<PedidoId> pedidosId) {
		this.pedidosId = pedidosId;
	}

}

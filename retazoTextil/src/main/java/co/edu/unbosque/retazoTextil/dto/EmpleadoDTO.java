package co.edu.unbosque.retazoTextil.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EmpleadoDTO {

	private Integer idEmpleado;
	private String primerNombreEmpleado;
	private String segundoNombreEmpleado;
	private String primerApellidoEmpleado;
	private String contrasenia;
	private String segundoApellidoEmpleado;
	private String telefonoEmpleado;
	private String direccionEmpleado;
	private String paisNacimiento;
	private String ciudadNacimiento;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;
	private BigDecimal salario;

	private List<Integer> ventasIds;

	public EmpleadoDTO() {
	}

	public EmpleadoDTO(Integer idEmpleado, String primerNombreEmpleado, String segundoNombreEmpleado,
			String primerApellidoEmpleado, String contrasenia, String segundoApellidoEmpleado, String telefonoEmpleado,
			String direccionEmpleado, String paisNacimiento, String ciudadNacimiento, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, BigDecimal salario, List<Integer> ventasIds) {
		super();
		this.idEmpleado = idEmpleado;
		this.primerNombreEmpleado = primerNombreEmpleado;
		this.segundoNombreEmpleado = segundoNombreEmpleado;
		this.primerApellidoEmpleado = primerApellidoEmpleado;
		this.contrasenia = contrasenia;
		this.segundoApellidoEmpleado = segundoApellidoEmpleado;
		this.telefonoEmpleado = telefonoEmpleado;
		this.direccionEmpleado = direccionEmpleado;
		this.paisNacimiento = paisNacimiento;
		this.ciudadNacimiento = ciudadNacimiento;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.ventasIds = ventasIds;
	}

	public EmpleadoDTO(String primerNombreEmpleado, String contrasenia, String segundoNombreEmpleado,
			String primerApellidoEmpleado, String segundoApellidoEmpleado, String telefonoEmpleado,
			String direccionEmpleado, String paisNacimiento, String ciudadNacimiento, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, BigDecimal salario, List<Integer> ventasIds) {
		this.primerNombreEmpleado = primerNombreEmpleado;
		this.segundoNombreEmpleado = segundoNombreEmpleado;
		this.primerApellidoEmpleado = primerApellidoEmpleado;
		this.segundoApellidoEmpleado = segundoApellidoEmpleado;
		this.telefonoEmpleado = telefonoEmpleado;
		this.direccionEmpleado = direccionEmpleado;
		this.paisNacimiento = paisNacimiento;
		this.ciudadNacimiento = ciudadNacimiento;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.ventasIds = ventasIds;

		this.contrasenia = contrasenia;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getPrimerNombreEmpleado() {
		return primerNombreEmpleado;
	}

	public void setPrimerNombreEmpleado(String primerNombreEmpleado) {
		this.primerNombreEmpleado = primerNombreEmpleado;
	}

	public String getSegundoNombreEmpleado() {
		return segundoNombreEmpleado;
	}

	public void setSegundoNombreEmpleado(String segundoNombreEmpleado) {
		this.segundoNombreEmpleado = segundoNombreEmpleado;
	}

	public String getPrimerApellidoEmpleado() {
		return primerApellidoEmpleado;
	}

	public void setPrimerApellidoEmpleado(String primerApellidoEmpleado) {
		this.primerApellidoEmpleado = primerApellidoEmpleado;
	}

	public String getSegundoApellidoEmpleado() {
		return segundoApellidoEmpleado;
	}

	public void setSegundoApellidoEmpleado(String segundoApellidoEmpleado) {
		this.segundoApellidoEmpleado = segundoApellidoEmpleado;
	}

	public String getTelefonoEmpleado() {
		return telefonoEmpleado;
	}

	public void setTelefonoEmpleado(String telefonoEmpleado) {
		this.telefonoEmpleado = telefonoEmpleado;
	}

	public String getDireccionEmpleado() {
		return direccionEmpleado;
	}

	public void setDireccionEmpleado(String direccionEmpleado) {
		this.direccionEmpleado = direccionEmpleado;
	}

	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	public String getCiudadNacimiento() {
		return ciudadNacimiento;
	}

	public void setCiudadNacimiento(String ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public List<Integer> getVentasIds() {
		return ventasIds;
	}

	public void setVentasIds(List<Integer> ventasIds) {
		this.ventasIds = ventasIds;
	}

}

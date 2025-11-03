package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "empleado")
public class Empleado {

	@Id
	@Column(name = "id_empleado")
	private Integer idEmpleado;

	@Column(name = "contrasenia", nullable = false, length = 255)
	private String contrasenia;

	@Column(name = "primer_nombre_empleado", nullable = false, length = 30)
	private String primerNombreEmpleado;

	@Column(name = "segundo_nombre_empleado", length = 30)
	private String segundoNombreEmpleado;

	@Column(name = "primer_apellido_empleado", nullable = false, length = 30)
	private String primerApellidoEmpleado;

	@Column(name = "segundo_apellido_empleado", length = 30)
	private String segundoApellidoEmpleado;

	@Column(name = "telefono_empleado", nullable = false, length = 20)
	private String telefonoEmpleado;

	@Column(name = "direccion_empleado", nullable = false, length = 100)
	private String direccionEmpleado;

	@Column(name = "pais_nacimiento", nullable = false, length = 30)
	private String paisNacimiento;

	@Column(name = "ciudad_nacimiento", nullable = false, length = 30)
	private String ciudadNacimiento;

	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	@Column(name = "fecha_ingreso", nullable = false)
	private LocalDate fechaIngreso;

	@Column(nullable = false)
	private Double salario;

	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Venta> ventas;

	@OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Administrador administrador;

	@OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Vendedor vendedor;

	public Empleado() {
	}

	public Empleado(Integer idEmpleado, String contrasenia, String primerNombreEmpleado, String segundoNombreEmpleado,
			String primerApellidoEmpleado, String segundoApellidoEmpleado, String telefonoEmpleado,
			String direccionEmpleado, String paisNacimiento, String ciudadNacimiento, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, Double salario, List<Venta> ventas, Administrador administrador,
			Vendedor vendedor) {
		super();
		this.idEmpleado = idEmpleado;
		this.contrasenia = contrasenia;
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
		this.ventas = ventas;
		this.administrador = administrador;
		this.vendedor = vendedor;
	}

	public Empleado(String primerNombreEmpleado, String segundoNombreEmpleado, String primerApellidoEmpleado,
			String segundoApellidoEmpleado, String contrasenia, String telefonoEmpleado, String direccionEmpleado,
			String paisNacimiento, String ciudadNacimiento, LocalDate fechaNacimiento, LocalDate fechaIngreso,
			Double salario) {
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
		this.contrasenia = contrasenia;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
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

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

}

package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "numero_cubiculo", nullable = false, unique = true)
    private Integer numeroCubiculo;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @Column(name = "fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Proveedor> proveedores;

    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Contactar> contactos;

    public Administrador() {}

    public Administrador(Integer numeroCubiculo, LocalDate fechaSolicitud, LocalDate fechaEntrega, Empleado empleado,
			List<Proveedor> proveedores, List<Contactar> contactos) {
		
		this.numeroCubiculo = numeroCubiculo;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
		this.empleado = empleado;
		this.proveedores = proveedores;
		this.contactos = contactos;
	}

	public Administrador(Integer numeroCubiculo, LocalDate fechaSolicitud, LocalDate fechaEntrega, Empleado empleado, List<Proveedor> pro) {
        this.numeroCubiculo = numeroCubiculo;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEntrega = fechaEntrega;
        this.empleado = empleado;
    }

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getNumeroCubiculo() {
		return numeroCubiculo;
	}

	public void setNumeroCubiculo(Integer numeroCubiculo) {
		this.numeroCubiculo = numeroCubiculo;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public List<Contactar> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contactar> contactos) {
		this.contactos = contactos;
	}
    
    
}

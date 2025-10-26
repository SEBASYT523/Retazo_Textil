package co.edu.unbosque.retazoTextil.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "primer_nombre_proveedor", nullable = false, length = 30)
    private String primerNombreProveedor;

    @Column(name = "segundo_nombre_proveedor", length = 30)
    private String segundoNombreProveedor;

    @Column(name = "primer_apellido_proveedor", nullable = false, length = 30)
    private String primerApellidoProveedor;

    @Column(name = "segundo_apellido_proveedor", length = 30)
    private String segundoApellidoProveedor;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "ciudad", length = 30)
    private String ciudad;

    @Column(name = "pais", nullable = false, length = 30)
    private String pais;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @Column(name = "fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrador_numero_cubiculo", nullable = false)
    private Administrador administrador;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Producto> productos;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Contactar> contactos;

    public Proveedor() {}

    public Proveedor(String primerNombreProveedor, String primerApellidoProveedor, String telefono, String pais, LocalDate fechaSolicitud, LocalDate fechaEntrega, Administrador administrador) {
        this.primerNombreProveedor = primerNombreProveedor;
        this.primerApellidoProveedor = primerApellidoProveedor;
        this.telefono = telefono;
        this.pais = pais;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEntrega = fechaEntrega;
        this.administrador = administrador;
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

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Contactar> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contactar> contactos) {
		this.contactos = contactos;
	}
    
    
}

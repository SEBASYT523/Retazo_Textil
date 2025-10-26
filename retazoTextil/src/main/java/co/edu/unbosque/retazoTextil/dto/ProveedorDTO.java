package co.edu.unbosque.retazoTextil.dto;


import java.time.LocalDate;
import java.util.List;


public class ProveedorDTO {

    private Integer idProveedor;

    private String primerNombreProveedor;

    private String segundoNombreProveedor;

    private String primerApellidoProveedor;

    private String segundoApellidoProveedor;

    private String telefono;

    private String ciudad;

    private String pais;

    private LocalDate fechaSolicitud;

    private LocalDate fechaEntrega;

   
    private Integer administradorId;

    private List<Integer> productosId;

    private List<Integer> contactosId;

    public ProveedorDTO() {}

    public ProveedorDTO(String primerNombreProveedor, String primerApellidoProveedor, String telefono, String pais, LocalDate fechaSolicitud, LocalDate fechaEntrega, Integer administradorId) {
        this.primerNombreProveedor = primerNombreProveedor;
        this.primerApellidoProveedor = primerApellidoProveedor;
        this.telefono = telefono;
        this.pais = pais;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEntrega = fechaEntrega;
        this.administradorId = administradorId;
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

	public List<Integer> getContactos() {
		return contactosId;
	}

	public void setContactos(List<Integer> contactosId) {
		this.contactosId = contactosId;
	}
    
    
}

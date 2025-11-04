package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.edu.unbosque.model.ProveedorDTO;
import co.edu.unbosque.model.persistence.ExternalHTTPRequestHandler;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("ProveedorBean")
@SessionScoped
public class ProveedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ProveedorDTO> proveedores = new ArrayList<>();
	private ProveedorDTO proveedorNuevo = new ProveedorDTO();
	private ProveedorDTO proveedorSeleccionado = new ProveedorDTO();

	private final String BASE_URL = "http://localhost:8080/proveedor";
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private ProveedorDTO nuevoProveedor = new ProveedorDTO();

	@PostConstruct
	public void init() {
		cargarProveedores();
	}

	public String cargarProveedores() {
		try {

			proveedores = ExternalHTTPRequestHandler.doGetAndConvertToDTOListProveedor(BASE_URL + "/all");

			return "";
		} catch (Exception e) {
			e.printStackTrace();
			proveedores = new ArrayList<>();
			return "";
		}
	}

	public void crearProveedor() {
		try {
			nuevoProveedor.setIdProveedor(1);
			nuevoProveedor.setAdministradorId(1);
			String json = gson.toJson(nuevoProveedor);
			ExternalHTTPRequestHandler.doPost(BASE_URL + "/create", json);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Proveedor creado correctamente"));

			cargarProveedores(); 
			nuevoProveedor = new ProveedorDTO(); 
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo crear el proveedor"));
		}
	}

	public ProveedorDTO getNuevoProveedor() {
		return nuevoProveedor;
	}

	public void setNuevoProveedor(ProveedorDTO nuevoProveedor) {
		this.nuevoProveedor = nuevoProveedor;
	}

	public void editarProveedor(ProveedorDTO prov) {
		System.out.println(prov.getIdProveedor());
		this.proveedorSeleccionado = prov;
	}

	

	public void eliminarProveedor(int id) {
	    try {
	        System.out.println("Intentando eliminar proveedor con ID: " + id);
	        String respuesta = ExternalHTTPRequestHandler.doDelete(BASE_URL + "/delete/" + id);
	        System.out.println("Respuesta del servidor: " + respuesta);

	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado",
	                "Proveedor eliminado correctamente");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        cargarProveedores();
	    } catch (Exception e) {
	        e.printStackTrace();
	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
	    }
	}



	public List<ProveedorDTO> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorDTO> proveedores) {
		this.proveedores = proveedores;
	}

	public ProveedorDTO getProveedorNuevo() {
		return proveedorNuevo;
	}

	public void setProveedorNuevo(ProveedorDTO proveedorNuevo) {
		this.proveedorNuevo = proveedorNuevo;
	}

	public ProveedorDTO getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}

	public void setProveedorSeleccionado(ProveedorDTO proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
	}
}

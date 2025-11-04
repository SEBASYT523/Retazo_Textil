package co.edu.unbosque.beans;

import co.edu.unbosque.model.EmpleadoDTO;
import co.edu.unbosque.model.persistence.ExternalHTTPRequestHandler;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

@Named("empleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<EmpleadoDTO> empleados;  
    private EmpleadoDTO nuevoEmpleado;    

    private final String URL_BASE = "http://localhost:8080/empleados";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    
    @PostConstruct
    public void init() {
        empleados = new ArrayList<>();
        nuevoEmpleado = new EmpleadoDTO();
        cargarEmpleados();
    }

    // ✅ Getters/Setters correctos
    public List<EmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public EmpleadoDTO getNuevoEmpleado() {
        return nuevoEmpleado;
    }

    public void setNuevoEmpleado(EmpleadoDTO nuevoEmpleado) {
        this.nuevoEmpleado = nuevoEmpleado;
    }

    // ✅ Cargar empleados desde backend
    public String cargarEmpleados() {
        try {
            empleados = ExternalHTTPRequestHandler.doGetAndConvertToDTOListEmpleado(URL_BASE + "/listar");
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            empleados = new ArrayList<>();
            return "";
        }
    }

    // ✅ Crear empleado
    public void crearEmpleado() {
        try {
            String json = gson.toJson(nuevoEmpleado);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_BASE + "/crear"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            cargarEmpleados();
            nuevoEmpleado = new EmpleadoDTO();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ✅ Eliminar empleado
    public void eliminarEmpleado(Integer idEmpleado) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_BASE + "/eliminar/" + idEmpleado))
                    .DELETE()
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            cargarEmpleados();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Cerrar sesión
    public String cerrarSesion() {
        return "index?faces-redirect=true";
    }
}

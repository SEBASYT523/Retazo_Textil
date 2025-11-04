package co.edu.unbosque.beans;

import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import co.edu.unbosque.model.ContactarDTO;

@Named("ContactarBean")
@SessionScoped
public class ContactarBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final String BASE_URL = "http://localhost:8080/contactar";

    private List<ContactarDTO> contactos;
    private ContactarDTO nuevoContacto = new ContactarDTO();

    private Gson gson = new Gson();

    @PostConstruct
    public void init() {
        cargarContactos();
    }

    public void cargarContactos() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            contactos = gson.fromJson(response.body(), new TypeToken<List<ContactarDTO>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearContacto() {
        try {
            nuevoContacto.setFechaSolicitud(LocalDate.now()); 
            String json = gson.toJson(nuevoContacto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            cargarContactos();
            nuevoContacto = new ContactarDTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarContacto(Integer proveedorId, Integer administradorId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + proveedorId + "/" + administradorId))
                    .DELETE()
                    .build();

            CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            cargarContactos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters y setters
    public List<ContactarDTO> getContactos() {
        return contactos;
    }

    public void setContactos(List<ContactarDTO> contactos) {
        this.contactos = contactos;
    }

    public ContactarDTO getNuevoContacto() {
        return nuevoContacto;
    }

    public void setNuevoContacto(ContactarDTO nuevoContacto) {
        this.nuevoContacto = nuevoContacto;
    }
    
    
}

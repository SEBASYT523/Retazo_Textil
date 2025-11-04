package co.edu.unbosque.beans;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import co.edu.unbosque.model.LocalDTO;
import co.edu.unbosque.model.persistence.ExternalHTTPRequestHandler;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;


@Named("LocalBean")
@SessionScoped
public class LocalBean implements Serializable {

    private List<LocalDTO> locales;
    private LocalDTO nuevoLocal = new LocalDTO();
    private final String URL_BASE = "http://localhost:8080/locales";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    @PostConstruct
    public void init() {
    	locales = new ArrayList<>();
    }

    public String cargarLocales() {
		try {

			locales = ExternalHTTPRequestHandler.doGetAndConvertToDTOListLocal(URL_BASE + "/listar");

			return "";
		} catch (Exception e) {
			e.printStackTrace();
			locales = new ArrayList<>();
			return "";
		}
	}

    public void crearLocal() {
        try {
            String json = gson.toJson(nuevoLocal);
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + "/crear"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            cargarLocales();
            nuevoLocal = new LocalDTO();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eliminarLocal(Integer id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + "/eliminar/" + id))
                .DELETE().build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            cargarLocales();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<LocalDTO> getLocales() {
        return locales;
    }

    public void setLocales(List<LocalDTO> locales) {
        this.locales = locales;
    }

    public LocalDTO getNuevoLocal() {
        return nuevoLocal;
    }

    public void setNuevoLocal(LocalDTO nuevoLocal) {
        this.nuevoLocal = nuevoLocal;
    }
}

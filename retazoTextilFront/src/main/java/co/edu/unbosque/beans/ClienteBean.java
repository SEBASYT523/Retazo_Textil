package co.edu.unbosque.beans;

import java.io.Serializable;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.persistence.ExternalHTTPRequestHandler;
import co.edu.unbosque.util.LocalDateAdapter;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("LoginBean")
@SessionScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ClienteDTO nuevoCliente;
    private final String URL_CREAR = "http://localhost:8080/clientes/crear";

    @PostConstruct
    public void init() {
        nuevoCliente = new ClienteDTO();
    }

    public void registrarCliente() {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            String json = gson.toJson(nuevoCliente);
            String respuesta = ExternalHTTPRequestHandler.doPostRegister(URL_CREAR, json);

            if (respuesta.startsWith("200")) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente creado exitosamente", null));
                nuevoCliente = new ClienteDTO(); 
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cliente: " + respuesta, null));
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error inesperado: " + e.getMessage(), null));
        }
    }

    public ClienteDTO getNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(ClienteDTO nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }
}

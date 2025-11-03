package co.edu.unbosque.beans;

import java.io.Serializable;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.persistence.ExternalHTTPRequestHandler;
import co.edu.unbosque.util.LocalDateAdapter;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("ClienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCliente;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String contrasenia;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;

    private ClienteDTO clienteActual;

    private static final String URL_REGISTRO = "http://localhost:8080/clientes/crear";
    private static final String URL_LOGIN = "http://localhost:8080/clientes/login";

    @PostConstruct
    public void init() {
        fechaNacimiento = LocalDate.now();
    }

    public String registrarCliente() {
        try {
            ClienteDTO cliente = new ClienteDTO(idCliente, primerNombre, segundoNombre, primerApellido, segundoApellido,
                    contrasenia, telefono, direccion, fechaNacimiento);

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            String json = gson.toJson(cliente);

            String response = ExternalHTTPRequestHandler.doPostRegister(URL_REGISTRO, json);

            if (response.startsWith("200")) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso 🎉",
                                "Tu cuenta fue creada correctamente."));
                return "login-cliente.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error ❌",
                                "No se pudo registrar el cliente. Código: " + response));
                return null;
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error inesperado ⚠️", e.getMessage()));
            e.printStackTrace();
            return null;
        }
    }

    public String loginCliente() {
        try {
            if (idCliente == null || contrasenia == null || contrasenia.isBlank()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN, "Campos requeridos", "Debes ingresar tu ID y contraseña"));
                return null;
            }

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            ClienteDTO data = new ClienteDTO();
            data.setIdCliente(idCliente);
            data.setContrasenia(contrasenia);
            String json = gson.toJson(data);

            String response = ExternalHTTPRequestHandler.doPostRegister(URL_LOGIN, json);
            
            String[] division= response.split("\n");
            
            if (division[0].contains("200")) {
                clienteActual = gson.fromJson(division[1], ClienteDTO.class);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Bienvenido " + clienteActual.getPrimerNombre(),
                                "Inicio de sesión exitoso ✅"));

                return "dashboard-cliente.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error de inicio de sesión ❌",
                                "Credenciales incorrectas o usuario no encontrado"));
                return null;
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Error inesperado ⚠️", e.getMessage()));
            e.printStackTrace();
            return null;
        }
    }


    public ClienteDTO getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(ClienteDTO clienteActual) {
        this.clienteActual = clienteActual;
    }

    public boolean isLogueado() {
        return clienteActual != null && clienteActual.getIdCliente() != null;
    }

    public String getNombreCompleto() {
        if (clienteActual == null) return "";
        return clienteActual.getPrimerNombre() + " " + clienteActual.getPrimerApellido();
    }

    public String cerrarSesion() {
        clienteActual = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }


    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
}

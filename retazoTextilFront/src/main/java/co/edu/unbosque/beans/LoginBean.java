package co.edu.unbosque.beans;

import java.io.Serializable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.edu.unbosque.model.persistence.ExternalHTTPRequestHandler;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("LoginBean")
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idEmpleado;
	private String contrasenia;

	private String idCliente;
	private String contraseniaCliente;

	public LoginBean() {

	}

	public String loginVendedor() {
		if (idEmpleado == null || idEmpleado.isBlank() || contrasenia == null || contrasenia.isBlank()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar usuario y contraseña", null));
			return null;
		}

		try {
			JsonObject json = new JsonObject();
			json.addProperty("idEmpleado", this.idEmpleado);
			json.addProperty("contrasenia", this.contrasenia);

			String resp = ExternalHTTPRequestHandler.doPostLoginVendedor("http://localhost:8080/empleados/login",
					json.toString());

			if (resp == null || resp.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vuelva y intente mas tarde.", null));
				return null;
			}

			JsonObject jsonResp = JsonParser.parseString(resp).getAsJsonObject();
			String rol = jsonResp.get("rol").getAsString();

			if ("ADMIN".equalsIgnoreCase(rol)) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Login exitoso", null));
				return "admin?faces-redirect=true";
			} else if ("VENDEDOR".equalsIgnoreCase(rol)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Login exitoso", null));

				return "cliente?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseña o usuario invalido", null));
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Vuelva y intente mas tarde! " + e.getMessage(), null));
			return null;
		}
	}

	public String loginCliente() {
		if (idCliente == null || idCliente.isBlank() || contraseniaCliente == null || contraseniaCliente.isBlank()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar usuario y contraseña", null));
			return null;
		}

		try {
			JsonObject json = new JsonObject();
			json.addProperty("idCliente", this.idCliente);
			json.addProperty("contrasenia", this.contraseniaCliente);

			String resp = ExternalHTTPRequestHandler.doPostLogin("http://localhost:8080/clientes/login",
					json.toString());

			if (resp == null || resp.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vuelva y intente mas tarde.", null));
				return null;
			}
		
			String[] code = resp.split("\n");

			if (code[0].contains("200")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Login exitoso", null));
				return "cliente?faces-redirect=true";
				
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseña o usuario invalido", null));
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Vuelva y intente mas tarde! " + e.getMessage(), null));
			return null;
		}
	}

	public String logout() {
		return "index?faces-redirect=true";
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getContraseniaCliente() {
		return contraseniaCliente;
	}

	public void setContraseniaCliente(String contraseniaCliente) {
		this.contraseniaCliente = contraseniaCliente;
	}

}

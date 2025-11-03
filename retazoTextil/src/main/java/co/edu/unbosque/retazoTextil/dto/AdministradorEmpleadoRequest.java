package co.edu.unbosque.retazoTextil.dto;

public class AdministradorEmpleadoRequest {

    private AdministradorDTO administrador;
    private EmpleadoDTO empleado;

    public AdministradorDTO getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorDTO administrador) {
        this.administrador = administrador;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }
}

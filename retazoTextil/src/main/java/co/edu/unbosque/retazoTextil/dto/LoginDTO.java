package co.edu.unbosque.retazoTextil.dto;

public class LoginDTO {

    private EmpleadoDTO empleado;
    private String rol;

    public LoginDTO() {
    }

    public LoginDTO(EmpleadoDTO empleado, String rol) {
        this.empleado = empleado;
        this.rol = rol;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

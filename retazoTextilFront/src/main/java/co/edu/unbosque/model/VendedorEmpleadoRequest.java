package co.edu.unbosque.model;

public class VendedorEmpleadoRequest {

    private VendedorDTO vendedor;
    private EmpleadoDTO empleado;

    public VendedorDTO getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorDTO vendedor) {
        this.vendedor = vendedor;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }
}

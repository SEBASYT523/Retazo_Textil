package co.edu.unbosque.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContactarId implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer proveedorIdProveedor;
	private Integer administradorIdEmpleado;

	public ContactarId() {
	}

	public ContactarId(Integer proveedorIdProveedor, Integer administradorIdEmpleado) {
		this.proveedorIdProveedor = proveedorIdProveedor;
		this.administradorIdEmpleado = administradorIdEmpleado;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ContactarId))
			return false;
		ContactarId that = (ContactarId) o;
		return Objects.equals(proveedorIdProveedor, that.proveedorIdProveedor)
				&& Objects.equals(administradorIdEmpleado, that.administradorIdEmpleado);
	}

	@Override
	public int hashCode() {
		return Objects.hash(proveedorIdProveedor, administradorIdEmpleado);
	}

	public Integer getProveedorIdProveedor() {
		return proveedorIdProveedor;
	}

	public void setProveedorIdProveedor(Integer proveedorIdProveedor) {
		this.proveedorIdProveedor = proveedorIdProveedor;
	}

	public Integer getAdministradorIdEmpleado() {
		return administradorIdEmpleado;
	}

	public void setAdministradorIdEmpleado(Integer administradorIdEmpleado) {
		this.administradorIdEmpleado = administradorIdEmpleado;
	}

}

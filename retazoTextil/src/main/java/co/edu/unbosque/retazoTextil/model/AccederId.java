package co.edu.unbosque.retazoTextil.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccederId implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Integer bodegaNumeroBodega;
	private Integer vendedorIdEmpleado;

	public AccederId() {
	}

	public AccederId(Integer bodegaNumeroBodega, Integer vendedorIdEmpleado) {
		this.bodegaNumeroBodega = bodegaNumeroBodega;
		this.vendedorIdEmpleado = vendedorIdEmpleado;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AccederId))
			return false;
		AccederId that = (AccederId) o;
		return Objects.equals(bodegaNumeroBodega, that.bodegaNumeroBodega)
				&& Objects.equals(vendedorIdEmpleado, that.vendedorIdEmpleado);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bodegaNumeroBodega, vendedorIdEmpleado);
	}

	public Integer getBodegaNumeroBodega() {
		return bodegaNumeroBodega;
	}

	public void setBodegaNumeroBodega(Integer bodegaNumeroBodega) {
		this.bodegaNumeroBodega = bodegaNumeroBodega;
	}

	public Integer getVendedorIdEmpleado() {
		return vendedorIdEmpleado;
	}

	public void setVendedorIdEmpleado(Integer vendedorIdEmpleado) {
		this.vendedorIdEmpleado = vendedorIdEmpleado;
	}

}

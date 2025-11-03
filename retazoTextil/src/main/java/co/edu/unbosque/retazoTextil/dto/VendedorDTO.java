package co.edu.unbosque.retazoTextil.dto;

public class VendedorDTO {

	private Integer idEmpleado;

	private Integer numeroProductosVendidos;

	private Integer totalVentas;

	private Integer localId;

	public VendedorDTO() {

	}

	public VendedorDTO(Integer numeroProductosVendidos, Integer totalVentas, Integer local, Integer empleado) {
		this.numeroProductosVendidos = numeroProductosVendidos;
		this.totalVentas = totalVentas;
		this.localId = local;
	
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getNumeroProductosVendidos() {
		return numeroProductosVendidos;
	}

	public void setNumeroProductosVendidos(Integer numeroProductosVendidos) {
		this.numeroProductosVendidos = numeroProductosVendidos;
	}

	public Integer getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(Integer totalVentas) {
		this.totalVentas = totalVentas;
	}

	public Integer getLocalId() {
		return localId;
	}

	public void setLocalId(Integer localId) {
		this.localId = localId;
	}

}

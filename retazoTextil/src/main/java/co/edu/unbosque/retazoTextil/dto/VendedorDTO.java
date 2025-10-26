package co.edu.unbosque.retazoTextil.dto;

public class VendedorDTO {

	private Long idEmpleado;

	private Integer numeroProductosVendidos;

	private Integer totalVentas;

	private Integer localId;

	private Integer empleadoId;

	public VendedorDTO() {

	}

	public VendedorDTO(Integer numeroProductosVendidos, Integer totalVentas, Integer local, Integer empleado) {
		this.numeroProductosVendidos = numeroProductosVendidos;
		this.totalVentas = totalVentas;
		this.localId = local;
		this.empleadoId = empleado;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
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

	public Integer getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Integer empleadoId) {
		this.empleadoId = empleadoId;
	}

}

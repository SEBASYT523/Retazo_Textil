package co.edu.unbosque.retazoTextil.dto;

public class AccederDTO {

	private Integer bodegaId;
	private Integer vendedorId;

	public AccederDTO() {
	}

	public AccederDTO(Integer bodegaId, Integer vendedorId) {
		super();
		this.bodegaId = bodegaId;
		this.vendedorId = vendedorId;
	}

	public Integer getBodegaId() {
		return bodegaId;
	}

	public void setBodegaId(Integer bodegaId) {
		this.bodegaId = bodegaId;
	}

	public Integer getVendedorId() {
		return vendedorId;
	}

	public void setVendedorId(Integer vendedorId) {
		this.vendedorId = vendedorId;
	}

}

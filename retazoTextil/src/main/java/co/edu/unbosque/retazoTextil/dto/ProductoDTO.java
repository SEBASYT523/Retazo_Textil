package co.edu.unbosque.retazoTextil.dto;

import java.math.BigDecimal;
import java.util.List;

import co.edu.unbosque.retazoTextil.model.GuardarId;

public class ProductoDTO {

	private Integer codProducto;

	private String nombre;

	private String tipoProducto;

	private String color;

	private BigDecimal precio;

	private Integer proveedorId;

	private List<GuardarId> guardadosIds;

	public ProductoDTO() {
	}

	public ProductoDTO(String nombre, String tipoProducto, String color, BigDecimal precio, Integer proveedorId) {
		this.nombre = nombre;
		this.tipoProducto = tipoProducto;
		this.color = color;
		this.precio = precio;
		this.proveedorId = proveedorId;
	}

	public ProductoDTO(String nombre, String tipoProducto, String color, BigDecimal precio,
			Integer proveedorId, List<GuardarId> guardadosIds) {
		this.nombre = nombre;
		this.tipoProducto = tipoProducto;
		this.color = color;
		this.precio = precio;
		
		this.proveedorId = proveedorId;
		this.guardadosIds = guardadosIds;
	}

	public Integer getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Integer codProducto) {
		this.codProducto = codProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(Integer proveedorId) {
		this.proveedorId = proveedorId;
	}

	public List<GuardarId> getGuardadosIds() {
		return guardadosIds;
	}

	public void setGuardadosIds(List<GuardarId> guardadosIds) {
		this.guardadosIds = guardadosIds;
	}

	public void setInteger(Integer proveedorId) {
		this.proveedorId = proveedorId;
	}

	public List<GuardarId> getGuardados() {
		return guardadosIds;
	}

	public void setGuardados(List<GuardarId> guardadosIds) {
		this.guardadosIds = guardadosIds;
	}

}

package co.edu.unbosque.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GuardarId implements Serializable {

  
	
	private static final long serialVersionUID = 1L;
	private Integer productoCodProducto;
    private Integer bodegaNumeroBodega;

    public GuardarId() {}

    public GuardarId(Integer productoCodProducto, Integer bodegaNumeroBodega) {
        this.productoCodProducto = productoCodProducto;
        this.bodegaNumeroBodega = bodegaNumeroBodega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuardarId)) return false;
        GuardarId that = (GuardarId) o;
        return Objects.equals(productoCodProducto, that.productoCodProducto) &&
               Objects.equals(bodegaNumeroBodega, that.bodegaNumeroBodega);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoCodProducto, bodegaNumeroBodega);
    }

	public Integer getProductoCodProducto() {
		return productoCodProducto;
	}

	public void setProductoCodProducto(Integer productoCodProducto) {
		this.productoCodProducto = productoCodProducto;
	}

	public Integer getBodegaNumeroBodega() {
		return bodegaNumeroBodega;
	}

	public void setBodegaNumeroBodega(Integer bodegaNumeroBodega) {
		this.bodegaNumeroBodega = bodegaNumeroBodega;
	}
    
    
}

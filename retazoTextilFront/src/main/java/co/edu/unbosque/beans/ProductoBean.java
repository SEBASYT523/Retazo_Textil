package co.edu.unbosque.beans;

import co.edu.unbosque.model.LocalDTO;
import co.edu.unbosque.model.ProductoDTO;
import co.edu.unbosque.model.persistence.ExternalHTTPRequestHandler;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

@Named("ProductoBean")
@ViewScoped
public class ProductoBean implements Serializable {

    private List<ProductoDTO> productos;  
    private ProductoDTO nuevoProducto;    


    private final String URL_BASE = "http://localhost:8080/producto";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    
    @PostConstruct
    public void init() {
        productos = new ArrayList<>();
        nuevoProducto = new ProductoDTO();
        cargarProductos();
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    public ProductoDTO getNuevoProducto() {
        return nuevoProducto;
    }

    public void setNuevoProducto(ProductoDTO nuevoProducto) {
        this.nuevoProducto = nuevoProducto;
    }


    public String cargarProductos() {
  		try {

  			productos = ExternalHTTPRequestHandler.doGetAndConvertToDTOListProducto(URL_BASE + "/listar");

  			return "";
  		} catch (Exception e) {
  			e.printStackTrace();
  			productos = new ArrayList<>();
  			return "";
  		}
  	}

      public void crearProducto() {
          try {
              String json = gson.toJson(nuevoProducto);
              HttpRequest request = HttpRequest.newBuilder()
                  .uri(URI.create(URL_BASE + "/crear"))
                  .header("Content-Type", "application/json")
                  .POST(HttpRequest.BodyPublishers.ofString(json))
                  .build();

              httpClient.send(request, HttpResponse.BodyHandlers.ofString());
              cargarProductos();
              nuevoProducto = new ProductoDTO();

          } catch (IOException | InterruptedException e) {
              e.printStackTrace();
          }
      }

    public void eliminarProducto(Integer codProducto) {
        productos.removeIf(p -> p.getCodProducto().equals(codProducto));
    }
}

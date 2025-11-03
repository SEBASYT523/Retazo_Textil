package co.edu.unbosque.model.persistence;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import co.edu.unbosque.model.PedidoDTO;
import co.edu.unbosque.model.EmpleadoDTO;
import co.edu.unbosque.model.PedidoDTO;
import co.edu.unbosque.util.LocalDateAdapter;

public class ExternalHTTPRequestHandler {
	protected static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(10)).build();

	public static String doPostRegister(String url, String json) {
		HttpRequest solicitud = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create(url)).header("Content-Type", "application/json").build();
		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(respuesta.statusCode());
		System.out.println(respuesta.body());
		return respuesta.statusCode() + "   \n   " + respuesta.body();
	}

	public static String doPostLogin(String url, String json) {
		HttpRequest solicitud = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create(url)).header("Content-Type", "application/json").build();
		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("🟢 Código HTTP: " + respuesta.statusCode());
		System.out.println("🟢 Cuerpo recibido: " + respuesta.body());
		return respuesta.statusCode() + "\n" + respuesta.body();
	}

	public static String doPostLoginVendedor(String url, String json) {
		try {
			HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(url))
					.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();

			HttpResponse<String> respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());

			System.out.println("🟢 Código HTTP: " + respuesta.statusCode());
			System.out.println("🟢 Cuerpo recibido: " + respuesta.body());

			if (respuesta.statusCode() == 200 && respuesta.body() != null) {
				return respuesta.body();
			} else {
				System.out.println("⚠️ Error HTTP: " + respuesta.statusCode());
				return null;
			}

		} catch (Exception e) {
			System.out.println("❌ Error en doPostLoginVendedor: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static String doPost(String url, String json, String token) {
		HttpRequest solicitud = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create(url)).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token).build();
		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return respuesta.statusCode() + "";
	}

	public static String doDelete(String url, String token) {
		HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create(url))
				.header("User-Agent", "Java 11 HttpClient Bot").header("Authorization", "Bearer " + token).build();
		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("status code -> " + response.statusCode());
		return response.statusCode() + "";
	}

	public static String doUpdate(String fullUrl, String json, String token) {

		HttpRequest req = HttpRequest.newBuilder().uri(URI.create(fullUrl))
				.PUT(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token).build();
		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return response.statusCode() + "";
	}

	public static String doGet(String url) {

		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.header("Content-Type", "application/json").header("Authorization", "Bearer ").build();
		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return response.body();
	}

	public static String doGetDetallado(String url, String token) {

		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + token).build();
		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return response.body();
	}

	public static String doGetAndParse(String url, String token) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + token).build();
		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("status code -> " + response.statusCode());
		String uglyJson = response.body();
		return prettyPrintUsingGson(uglyJson);
	}

	public static String prettyPrintUsingGson(String uglyJson) {
		Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
		JsonElement jsonElement = JsonParser.parseString(uglyJson);
		String prettyJsonString = gson.toJson(jsonElement);
		return prettyJsonString;
	}

//	public static UserDTO doGetAndConvertToDTO(String url, String token) {
//		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
//				.header("Content-Type", "application/json").header("Authorization", "Bearer " + token).build();
//		HttpResponse<String> response = null;
//		try {
//			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
//		} catch (IOException | InterruptedException e) {
//			e.printStackTrace();
//		}
//		String json = response.body();
//		UserDTO fact = new GsonBuilder().create().fromJson(json, UserDTO.class);
//		return fact;
//	}

	public static List<EmpleadoDTO> doGetAndConvertToDTOList(String url) {
		HttpRequest solicitud = HttpRequest.newBuilder().GET().uri(URI.create(url)).header("Authorization", "Bearer ")
				.build();
		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		String json = respuesta.body().trim();
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
		if (json.startsWith("[")) {
			return Arrays.asList(gson.fromJson(json, EmpleadoDTO[].class));
		} else if (json.startsWith("{")) {
			JsonElement je = JsonParser.parseString(json);
			if (je.getAsJsonObject().has("data")) {
				return Arrays.asList(gson.fromJson(je.getAsJsonObject().get("data"), EmpleadoDTO[].class));
			} else {
				EmpleadoDTO obj = gson.fromJson(json, EmpleadoDTO.class);
				return Arrays.asList(obj);
			}
		} else {
			return List.of();
		}
	}
	
	
	public static List<PedidoDTO> doGetAndConvertToDTOListPedido(String url) {
	
		HttpRequest solicitud = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.header("Content-Type", "application/json").build();
		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		String json = respuesta.body().trim();
		Gson gson = new GsonBuilder().create();
		if (json.startsWith("[")) {
			return Arrays.asList(gson.fromJson(json, PedidoDTO[].class));
		} else if (json.startsWith("{")) {
			JsonElement je = JsonParser.parseString(json);
			if (je.getAsJsonObject().has("data")) {
				return Arrays.asList(gson.fromJson(je.getAsJsonObject().get("data"), PedidoDTO[].class));
			} else {
				PedidoDTO obj = gson.fromJson(json, PedidoDTO.class);
				return Arrays.asList(obj);
			}
		} else {
			return List.of();
		}
	}





	

}

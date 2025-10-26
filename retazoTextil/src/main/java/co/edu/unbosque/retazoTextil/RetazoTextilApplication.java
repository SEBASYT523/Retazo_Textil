package co.edu.unbosque.retazoTextil;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RetazoTextilApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetazoTextilApplication.class, args);
	}
	
	 @Bean
	  public ModelMapper getModelMapper() {
	    return new ModelMapper();
	  }

}

package be.thomasmore.converterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConverterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConverterServiceApplication.class, args);
	}

}

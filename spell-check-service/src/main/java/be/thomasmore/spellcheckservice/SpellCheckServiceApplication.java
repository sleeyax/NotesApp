package be.thomasmore.spellcheckservice;

import be.thomasmore.spellcheckservice.api.GrammarBotApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class SpellCheckServiceApplication {
	@Bean
	GrammarBotApi grammarBotApi() {
		return new GrammarBotApi();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpellCheckServiceApplication.class, args);
	}

}

package be.thomasmore.edgeservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("be.thomasmore.edgeservice"))
                //.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages())
                .globalResponseMessage(RequestMethod.POST, responseMessages())
                .globalResponseMessage(RequestMethod.PUT, responseMessages())
                .globalResponseMessage(RequestMethod.DELETE, responseMessages());

    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Notes REST API",
                "Java spring boot microservices",
                "v1.0.0",
                null,
                new Contact("Team 2", "https://github.com/sleeyax/NotesApp", null),
                null, null, Collections.emptyList());
    }

    private ArrayList<ResponseMessage> responseMessages() {
        return newArrayList(new ResponseMessageBuilder()
                .code(500)
                .message("microservice unavailable")
                .responseModel(new ModelRef("Error"))
                .build());
    }
}

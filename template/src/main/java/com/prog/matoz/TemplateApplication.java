package com.prog.matoz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class TemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}
	
	@Bean
	  public Docket docket() {
	    return new Docket(DocumentationType.OAS_30)
	        .apiInfo(new ApiInfoBuilder()
	            .title("Template API")
	            .description("A Template API")
	            .version("0.0.1-SNAPSHOT")
	            .license("MIT")
	            .licenseUrl("https://opensource.org/licenses/MIT")
	            .build())
	        .tags(new Tag("Template", "Endpoints for CRUD operations on template"))
	        .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	        .build();
	  }

}

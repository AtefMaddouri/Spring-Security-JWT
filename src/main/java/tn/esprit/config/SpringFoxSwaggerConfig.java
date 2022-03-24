package tn.esprit.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SpringFoxSwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiEndPointsInfo())
				.securityContexts(Collections.singletonList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("tn.esprit.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("Spring Security + JWT")
                .description("Workshop pour la 4SE3")
                .contact(new Contact("Atef MADDOURI", "https://www.linkedin.com/in/atef-maddouri/", "atef.maddouri@esprit.tn"))
                .version("1.0.0")
                .build();
    }

	private ApiKey apiKey() {
		return new ApiKey("Bearer", AUTHORIZATION_HEADER, "header");
	}
	
	
	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("Bearer", authorizationScopes)); 

	}
}

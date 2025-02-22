package com.restfull.api.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

// swagger api
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Demo API",                
        version = "1.0.0",                 
        description = "API ini digunakan untuk demo Spring Boot", 
        contact = @Contact(
            name = "Arif-Rizal",            
            email = "rizallarif32@gmail.com", 
            url = "https://example.com"    
        ),
        license = @License(
            name = "Apache 2.0",       
            url = "https://www.apache.org/licenses/LICENSE-2.0.html" 
        )
    )
)
public class SwaggerConfig {
    
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("public-api")
            .pathsToMatch("/api/v1**") // Perbaiki ini
            .build();
    }
    

    

}
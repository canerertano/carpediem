package com.carpediem.skeleton.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI carpediemSkeletonOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Carpediem Skeleton Project")
                        .description("It's a minimal project reference for new Spring Boot microservices projects")
                        .version("V1"));
    }
}

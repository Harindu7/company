package com.microservices.company.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8081");
        devServer.setDescription("Local Development Server Company Management API");

        Info info = new Info()
                .title("Company Management API")
                .version("1.0")
                .description("This API provides endpoints to manage company information.");

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}

package com.skhuthon.caffeinebalance.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Value("${serverUrl}")
    private String prodUrl;

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("CaffeineBalance API Document")
                .version("v0.0.1")
                .description("CaffeineBalance 문서");

        Server prodServer = new Server()
                .url(prodUrl)
                .description("Production Server");

        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Local Server");

        return new OpenAPI()
                .info(info)
                .servers(Arrays.asList(prodServer, localServer));
    }
}

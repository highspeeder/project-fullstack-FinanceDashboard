package com.dashboard.server.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Dashboard Api",
                version = "1.0.0",
                description = "Dashboard프로젝트 Api문서"),
        servers = {
                @Server(url = "http://localhost:8080", description = "기본 서버")
        })
@Configuration
public class SwaggerConfiguration {
}

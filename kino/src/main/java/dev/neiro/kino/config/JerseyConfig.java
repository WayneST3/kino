package dev.neiro.kino.config;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@OpenAPIDefinition(
        info = @Info(
                title = "Kino - REST API",
                version = "1.0",
                description = "Техническое описание программного интерфейса АИС «Kino»",
                license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"),
                termsOfService = "/docs/terms_of_service.pdf"
        ),
        externalDocs = @ExternalDocumentation(description = "Official website", url = "")
)
@Component
@PropertySource("file:${catalina.base}/conf/kino.properties")
@ApplicationPath("/v1")
public class JerseyConfig extends ResourceConfig {

    @Value("${swagger.url}")
    private String baseUrl;

    @PostConstruct
    private void init() {
        packages("dev.neiro.dental.controller.shared", "dev.neiro.dental.serializer");

        SwaggerConfiguration config = new SwaggerConfiguration();
        OpenAPI openAPI = new OpenAPI();
        openAPI.addServersItem(new Server().url(baseUrl).description("Базовый URL для всех запросов"));
        config.openAPI(openAPI);
        register(new OpenApiResource().openApiConfiguration(config));
    }
}

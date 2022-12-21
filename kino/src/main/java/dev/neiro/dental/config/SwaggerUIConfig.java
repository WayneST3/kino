package dev.neiro.dental.config;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.Constants;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springdoc.core.SwaggerUiOAuthProperties;
import org.springdoc.webmvc.ui.SwaggerIndexPageTransformer;
import org.springdoc.webmvc.ui.SwaggerIndexTransformer;
import org.springdoc.webmvc.ui.SwaggerWelcomeCommon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wayne Stark
 * @since 18.10.2022
 */
@Configuration
@Slf4j
public class SwaggerUIConfig {

    @Bean
    public SwaggerIndexTransformer swaggerIndexTransformer(
            SwaggerUiConfigProperties swaggerUiConfig,
            SwaggerUiOAuthProperties swaggerUiOAuthProperties,
            SwaggerUiConfigParameters swaggerUiConfigParameters,
            SwaggerWelcomeCommon swaggerWelcomeCommon) {
        return new SwaggerIndexPageTransformer(swaggerUiConfig, swaggerUiOAuthProperties, swaggerUiConfigParameters, swaggerWelcomeCommon) {
            @Override
            protected String overwriteSwaggerDefaultUrl(String html) {
                return html.replace(Constants.SWAGGER_UI_DEFAULT_URL, swaggerUiConfigParameters.getUrl())
                        .replaceAll("\"configUrl.*swagger-config\",", "");
            }
        };
    }

}

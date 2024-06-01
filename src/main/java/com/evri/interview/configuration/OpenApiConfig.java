package com.evri.interview.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for configuring OpenAPI resources.
 */
@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

    /**
     * Configures resource handlers for serving Swagger UI resources.
     *
     * @param registry ResourceHandlerRegistry to register resource handlers.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/")
                .resourceChain(false);
        // Check http://localhost:8080/swagger-ui/index.html for API Description
    }

}

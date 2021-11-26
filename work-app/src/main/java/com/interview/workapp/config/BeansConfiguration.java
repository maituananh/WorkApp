package com.interview.workapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Beans configuration.
 */
@Configuration
public class BeansConfiguration {

    /**
     * Model mapper model mapper.
     *
     * @return the model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    /**
     * Swagger open api.
     *
     * @return the open api
     */
    @Bean
    public OpenAPI swagger() {
        SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
        return swaggerConfiguration.api();
    }
}

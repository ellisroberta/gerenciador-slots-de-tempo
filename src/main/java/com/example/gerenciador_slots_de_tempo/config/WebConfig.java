package com.example.gerenciador_slots_de_tempo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    @Primary
    public RequestMappingHandlerAdapter customRequestMappingHandlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }

}
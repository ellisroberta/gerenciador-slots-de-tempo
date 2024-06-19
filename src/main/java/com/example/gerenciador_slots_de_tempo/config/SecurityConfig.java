package com.example.gerenciador_slots_de_tempo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/reservations/**", "/api/availabilities/**").permitAll() // Permite acesso irrestrito a esses endpoints
                .anyRequest().authenticated() // Exige autenticação para outros endpoints não especificados
                .and()
                .csrf().disable(); // Desabilita CSRF para simplificação do exemplo
    }
}

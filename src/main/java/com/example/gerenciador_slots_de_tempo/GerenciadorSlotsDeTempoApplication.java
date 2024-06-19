package com.example.gerenciador_slots_de_tempo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.gerenciador_slots_de_tempo"})
public class GerenciadorSlotsDeTempoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorSlotsDeTempoApplication.class, args);
	}

}

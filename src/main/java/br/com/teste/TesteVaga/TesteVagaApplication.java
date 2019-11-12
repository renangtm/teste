package br.com.teste.TesteVaga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class TesteVagaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteVagaApplication.class, args);
	}

}

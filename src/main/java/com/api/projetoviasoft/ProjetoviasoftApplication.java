package com.api.projetoviasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@RestController
public class ProjetoviasoftApplication {

	//@Scheduled(cron="0 0/1 * 1/1 * ?")
	public static void main(String[] args) {
		SpringApplication.run(ProjetoviasoftApplication.class, args);		
	}
	
	@GetMapping("/")
	public String index() {
		return "INICIANDO COM SPRINGBOOT";
	}

}

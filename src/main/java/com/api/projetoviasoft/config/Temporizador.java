package com.api.projetoviasoft.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.api.projetoviasoft.models.StatusServicoModel;
import com.api.projetoviasoft.services.StatusServicoService;

@Component
public class Temporizador {
	
	@Autowired
	private ParserComponent parser;
	
	@Autowired
	private StatusServicoService statusServicoService;
	
		
	@Scheduled(fixedDelay = 300000)
	public void populaTabela() throws IOException {
		List<StatusServicoModel> listaModel = parser.parse();
		statusServicoService.save(listaModel);
	}
}

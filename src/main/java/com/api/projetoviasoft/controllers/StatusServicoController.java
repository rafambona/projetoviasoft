package com.api.projetoviasoft.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.projetoviasoft.models.StatusServicoModel;
import com.api.projetoviasoft.services.StatusServicoService;

@RestController
@RequestMapping("/api")
public class StatusServicoController {
	
	@Autowired
	private StatusServicoService statusServicoService;
	
	@GetMapping("/getStatusAtualPorEstado")
	public ResponseEntity<List<Object>> getStatusAtualPorEstado() {		
		List<Object> dados = statusServicoService.findAll();
		return ResponseEntity.ok(dados);
	}
	
	@GetMapping("/getStatusAtualPorEstado/{estado}")
	public ResponseEntity<List<StatusServicoModel>> getStatusAtualPorEstado(@PathVariable("estado") String estado) {		
		List<StatusServicoModel> dados = statusServicoService.findByEstado(estado);
		return ResponseEntity.ok(dados);
	}
	
	@GetMapping("/getStatusAtualPorEstadoByDate/{dateParam}")
	public ResponseEntity<List<StatusServicoModel>> getStatusAtualPorEstadoByDate(@PathVariable("dateParam") String dateParam) throws ParseException {
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateParam);
		List<StatusServicoModel> dados = statusServicoService.findAllWithCreationTime(new Date(date.getTime()));
		return ResponseEntity.ok(dados);
	}
	
	@GetMapping("/getMaxError")
	public ResponseEntity<List<Object>> getMaxError() {
		List<Object> dados = statusServicoService.findMaxError();
		return ResponseEntity.ok(dados);
	}
}

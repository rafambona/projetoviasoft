package com.api.projetoviasoft.services;

import com.api.projetoviasoft.models.StatusServicoModel;
import com.api.projetoviasoft.repositories.StatusServicoRepository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class StatusServicoService {

	@Autowired
	private StatusServicoRepository statusServicoRepository;

	@Transactional
	public void save(List<StatusServicoModel> statusServicoModel) {
		for (StatusServicoModel s : statusServicoModel) {
			statusServicoRepository.save(s);
		}
	}
	
	public List<Object> findAll() {
		return statusServicoRepository.getAll();
	}
	
	public List<StatusServicoModel> findByEstado(String estado){
		return statusServicoRepository.findByEstado(estado);
	}
	
	public List<StatusServicoModel> findAllWithCreationTime(@Param("dateParam") Date dateParam){		
		return statusServicoRepository.findAllWithCreationTime(dateParam);
	}
	
	public List<Object> findMaxError(){		
		return statusServicoRepository.findMaxError();
	}
}

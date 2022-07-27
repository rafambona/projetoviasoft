package com.api.projetoviasoft.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "TB_STATUS_SERVICO")
public class StatusServicoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 6)
	private String estado;
	@Column(nullable = false, length = 50)
	private String statusCor;
	@Column(nullable = false, length = 50)
	private String servico;		
	@Column
	@Temporal(TemporalType.TIMESTAMP)	
	private Date created = new Date();
	
	public StatusServicoModel() {		
	}

	public StatusServicoModel(String estado, String statusCor, String servico) {
		super();
		this.estado = estado;
		this.statusCor = statusCor;
		this.servico = servico;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getStatusCor() {
		return statusCor;
	}
	
	public void setStatusCor(String statusCor) {
		this.statusCor = statusCor;
	}
	
	public String getServico() {
		return servico;
	}
	
	public void setServico(String servico) {
		this.servico = servico;
	}
	

}

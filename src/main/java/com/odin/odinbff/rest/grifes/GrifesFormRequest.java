package com.odin.odinbff.rest.grifes;

import com.odin.odinbff.model.Grifes; 

import jakarta.websocket.ClientEndpoint;

public class GrifesFormRequest {
	
	private Long id;
	private String nome;
	
	
	public GrifesFormRequest(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public GrifesFormRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Grifes toModel() {
		
		return new Grifes(id,nome);
		
	}
	public static GrifesFormRequest fromModel(Grifes grifes) {
		return new GrifesFormRequest(grifes.getId(), grifes.getNome());
	}

}

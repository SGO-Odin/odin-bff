package com.odin.odinbff.rest.brands;

import com.odin.odinbff.model.Brands; 

import jakarta.websocket.ClientEndpoint;

public class BrandsFormRequest {
	
	private Long id;
	private String name;
	
	
	public BrandsFormRequest(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public BrandsFormRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Brands toModel() {
		
		return new Brands(id, name);
		
	}
	public static BrandsFormRequest fromModel(Brands brands) {
		return new BrandsFormRequest(brands.getId(), brands.getName());
	}

}

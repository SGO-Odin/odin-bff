package com.odin.odinbff.controller.brand;

import com.odin.odinbff.model.Brand;

public class BrandFormRequest {
	
	private Long id;
	private String name;
	
	
	public BrandFormRequest(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public BrandFormRequest() {
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
	public Brand toModel() {
		
		return new Brand(id, name);
		
	}
	public static BrandFormRequest fromModel(Brand brand) {
		return new BrandFormRequest(brand.getId(), brand.getName());
	}

}

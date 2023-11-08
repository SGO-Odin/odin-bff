package com.odin.odinbff.controller.brand;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.Brand;
import jakarta.validation.constraints.NotBlank;

public final class BrandFormRequest {
	@JsonProperty
	@NotBlank
	private final String name;

	@JsonCreator
	public BrandFormRequest(final String name) {
		this.name = name;
	}

	public Brand toModel(final Long id) {
		return new Brand(id, name);
	}

	public Brand toModel() {
		return toModel(null);
	}
}

package com.odin.odinbff.controller.brand;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.Brand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.web.bind.annotation.RequestBody;

public final class BrandFormRequest {
	private final String name;

	@JsonCreator
	public BrandFormRequest(final String name) {
		this.name = name;
	}

	@NotBlank
	@Length(min = Brand.Validations.Name.MIN_LENGTH, max = Brand.Validations.Name.MAX_LENGTH)
	public String getName() {
		return name;
	}

	public Brand toModel() {
		return new Brand(name);
	}
}

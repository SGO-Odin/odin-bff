package com.odin.odinbff.controller.product;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.EnumNamingStrategy;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.odin.odinbff.model.Product;
import com.odin.odinbff.repository.BrandRepository;
import com.odin.odinbff.repository.PurveyorReposioty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductFormRequest {

	@JsonProperty
	private final String reference;
	@JsonProperty
	private final String name;
	@JsonProperty
	@JsonEnumDefaultValue
	private final Product.UnitType unitType;
	@JsonProperty
	private final Long brand;
	@JsonProperty
	private final Long purveyor;
	@JsonProperty
	private final Boolean isActive;
	@JsonProperty
	private final Boolean inventoryControl;
	@JsonProperty
	private final BigDecimal purchaseCost;
	@JsonProperty
	private final BigDecimal currentSalePrice;
	@JsonProperty
	private final Short currentStock;
	@JsonProperty
	private final Short minStock;
	@JsonProperty
	private final String location;

	public ProductFormRequest(@NotBlank final String reference,
							  @NotBlank final String name,
							  @NotBlank final Product.UnitType unitType,
							  @NotNull final Long brands,
							  @NotNull final Long purveyor,
							  @NotNull final Boolean isActive,
							  @NotNull final Boolean isToStockControl,
							  @NotNull final BigDecimal purchaseCost,
							  @NotNull final BigDecimal currentSalePrice,
							  @NotNull final Short currentStock,
							  @NotNull final Short minStock,
							  final String location) {
		this.reference = reference;
		this.name = name;
		this.unitType = unitType;
		this.brand = brands;
		this.purveyor = purveyor;
		this.isActive = isActive;
		this.inventoryControl = isToStockControl;
		this.purchaseCost = purchaseCost;
		this.currentSalePrice = currentSalePrice;
		this.currentStock = currentStock;
		this.minStock = minStock;
		this.location = location;
	}

	public Product toModel(final BrandRepository brandRepository, final PurveyorReposioty purveyorReposioty) {
		return new Product(
				reference,
				name,
				unitType,
				brandRepository.getReferenceById(brand),
				purveyorReposioty.getReferenceById(purveyor),
				isActive,
				inventoryControl,
				purchaseCost,
				currentSalePrice,
				currentStock,
				minStock,
				location);
	}
}

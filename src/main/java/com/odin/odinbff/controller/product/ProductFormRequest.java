package com.odin.odinbff.controller.product;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.product.Product;
import com.odin.odinbff.repository.BrandRepository;
import com.odin.odinbff.repository.PurveyorReposioty;
import jakarta.validation.constraints.*;

public class ProductFormRequest {

	@JsonProperty
	private final String reference;
	@NotBlank
	@JsonProperty
	private final String name;
	@JsonProperty
	@NotNull
	@JsonEnumDefaultValue
	private final Product.UnitType unitType;
	@NotNull
	@JsonProperty
	private final Long brand;
	@JsonProperty
	@NotNull
	private final Long purveyor;
	@JsonProperty
	@NotNull
	private final Boolean isActive;
	@JsonProperty
	@NotNull
	private final Boolean inventoryControl;
	@JsonProperty
	@DecimalMin("0.05")
	private final BigDecimal purchaseCost;
	@JsonProperty
	@DecimalMin(value = "0.05")
	private final BigDecimal currentSalePrice;
	@PositiveOrZero
	@JsonProperty
	@NotNull
	private final Short currentStock;
	@JsonProperty
	@PositiveOrZero
	@NotNull
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
				inventoryControl,
				purchaseCost,
				currentSalePrice,
				currentStock,
				minStock,
				location);
	}
}

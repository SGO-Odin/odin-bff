package com.odin.odinbff.controller.product;

import java.math.BigDecimal;

import com.odin.odinbff.model.Product;
import com.odin.odinbff.repository.BrandRepository;
import com.odin.odinbff.repository.PurveyorReposioty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductFormRequest {

	private final String reference;
	private final String name;
	private final String unitType;
	private final Long brand;
	private final Long purveyor;
	private final boolean isActive;
	private final boolean inventoryControl;
	private final BigDecimal purchaseCost;
	private final BigDecimal currentSalePrice;
	private final Short currentStock;
	private final Short minStock;
	private final String location;
	
	public Product toModel(final BrandRepository brandRepository, final PurveyorReposioty purveyorReposioty) {
		return new Product(
				reference,
				name,
				Product.UnitType.valueOf(unitType),
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
	
	public ProductFormRequest(@NotBlank final String reference,
							  @NotBlank final String name,
							  @NotBlank final String unitType,
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
}

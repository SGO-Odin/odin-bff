package com.odin.odinbff.rest.products;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odin.odinbff.model.Product;;

public class ProductFormRequest {
	

	private Long id;
	private String reference;
	private String name;
	private String unit;
	private String brands;
	private String supplier;
	private boolean active;
	private boolean inventoryControl;
	private BigDecimal price;
	private BigDecimal profitPercentage;
	private BigDecimal profit;
	private BigDecimal sale;
	private String currentStock;
	private String minStock;
	private String location;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate registration;
	
	public Product toModel() {
		return new Product(
				id,
				reference,
				name,
				unit,
				brands,
				supplier,
				active,
				inventoryControl,
				price,
				profitPercentage,
				profit,
				sale,
				currentStock,
				minStock,
				location);
	}
	
	public ProductFormRequest(Long id, String reference, String name, String unit, String brands, String supplier,
			boolean active, boolean inventoryControl, BigDecimal price, BigDecimal profitPercentage, BigDecimal profit,
			BigDecimal sale, String currentStock, String minStock, String location) {
		super();
		this.id = id;
		this.reference = reference;
		this.name = name;
		this.unit = unit;
		this.brands = brands;
		this.supplier = supplier;
		this.active = active;
		this.inventoryControl = inventoryControl;
		this.price = price;
		this.profitPercentage = profitPercentage;
		this.profit = profit;
		this.sale = sale;
		this.currentStock = currentStock;
		this.minStock = minStock;
		this.location = location;
		
	}
	
	public ProductFormRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static ProductFormRequest fromModel(Product product) {
		return new ProductFormRequest(
				product.getId(),
				product.getSupplier(),
				product.getName(),
				product.getUnit(),
				product.getBrands(),
				product.getSupplier(),
				product.isActive(),
				product.isInventoryControl(),
				product.getPrice(),
				product.getProfitPercentage(),
				product.getProfit(),
				product.getSale(),
				product.getCurrentStock(),
				product.getMinStock(),
				product.getLocation());
		
	}
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isInventoryControl() {
		return inventoryControl;
	}
	public void setInventoryControl(boolean inventoryControl) {
		this.inventoryControl = inventoryControl;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getProfitPercentage() {
		return profitPercentage;
	}
	public void setProfitPercentage(BigDecimal profitPercentage) {
		this.profitPercentage = profitPercentage;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public BigDecimal getSale() {
		return sale;
	}
	public void setSale(BigDecimal sale) {
		this.sale = sale;
	}
	public String getCurrentStock() {
		return currentStock;
	}
	public void setCurrentStock(String currentStock) {
		this.currentStock = currentStock;
	}
	public String getMinStock() {
		return minStock;
	}
	public void setMinStock(String minStock) {
		this.minStock = minStock;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProductFormRequest [id=" + id + ", reference=" + reference + ", name=" + name + ", unit=" + unit
				+ ", brands=" + brands + ", supplier=" + supplier + ", active=" + active + ", inventoryControl=" + inventoryControl
				+ ", price=" + price + ", profitPercentage=" + profitPercentage + ", profit=" + profit + ", sale=" + sale
				+ ", currentStock=" + currentStock + ", minStock=" + minStock + ", location=" + location + "]";
	}
}

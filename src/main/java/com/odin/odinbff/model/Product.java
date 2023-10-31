package com.odin.odinbff.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;


@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 100)
    private String reference;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "unit", length = 50)
    private String unit;

    @Column(name = "brands", length = 150)
    private String brands;

    @Column(name = "supplier", length = 150)
    private String supplier;

    @Column(name = "active")
    private boolean active;


    @Column(name = "inventoryControl")
    private boolean inventoryControl;

    @Column(name = "price", precision = 16, scale = 2)
    private BigDecimal price;

    @Column(name = "profitPercentage", precision = 16, scale = 2)
    private BigDecimal profitPercentage;

    @Column(name = "profit", precision = 16, scale = 2)
    private BigDecimal profit;

    @Column(name = "sale", precision = 16, scale = 2)
    private BigDecimal sale;

    @Column(name = "currentStock", length = 150)
    private String currentStock;

    @Column(name = "minStock", length = 150)
    private String minStock;

    @Column(name = "location", length = 150)
    private String location;

    @Column(name = "registration_date")
    private LocalDate registrationDate;


    public Product(String reference, String name, String unit, String brands, String supplier, boolean active,
                   boolean inventoryControl, BigDecimal price, BigDecimal profitPercentage, BigDecimal profit, BigDecimal sale,
                   String currentStock, String minStock, String location) {
        super();
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

    public Product(Long id, String reference, String name, String unit, String brands, String supplier, boolean active,
                   boolean inventoryControl, BigDecimal price, BigDecimal profitPercentage, BigDecimal profit, BigDecimal sale,
                   String currentStock, String minStock, String location) {
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

    public Product() {
        super();
    }

    @PrePersist
    public void prePersist() {
        setRegistrationDate(LocalDate.now());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", reference=" + reference + ", name=" + name + ", unit=" + unit + ", brands="
                + brands + ", supplier=" + supplier + ", active=" + active + ", inventoryControl=" + inventoryControl
                + ", price=" + price + ", profitPercentage=" + profitPercentage + ", profit=" + profit + ", sale="
                + sale + ", currentStock=" + currentStock + ", minStock=" + minStock + ", location=" + location + "]";
    }
}

package com.odin.odinbff.model.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.odin.odinbff.model.Activatable;
import com.odin.odinbff.model.HasLongId;
import com.odin.odinbff.model.audit.HistoryLoggable;
import com.odin.odinbff.model.product.Brand;
import com.odin.odinbff.model.purveyor.Purveyor;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public final class Product extends HistoryLoggable<Product> implements HasLongId, Activatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(nullable = false, unique = true, length = 16)
    private final String reference;

    @Column(nullable = false, unique = true)
    private final String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final UnitType unit;

    @ManyToOne(optional = false)
    private final Brand brands;

    @ManyToOne(optional = false)
    private final Purveyor purveyor;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private final Boolean isToStockControl;

    @Column(nullable = false, precision = 16, scale = 2)
    private final BigDecimal purchaseCost;

    @Column(nullable = false, precision = 16, scale = 2)
    private final BigDecimal currentSalePrice;

    @Column(nullable = false)
    private final Short currentStock;

    @Column(nullable = false)
    private final Short minStock;

    private final String location;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime updatedOn;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private Product() {
        this(null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }


    private Product(final Long id,
                   final String reference,
                   final String name,
                   final UnitType unit,
                   final Brand brands,
                   final Purveyor purveyor,
                   final Boolean isActive,
                   final Boolean isToStockControl,
                   final BigDecimal purchaseCost,
                   final BigDecimal currentSalePrice,
                   final Short currentStock,
                   final Short minStock,
                   final String location,
                   final LocalDateTime createdOn,
                   final LocalDateTime updatedOn) {
        this.id = id;
        this.reference = reference;
        this.name = name;
        this.unit = unit;
        this.brands = brands;
        this.purveyor = purveyor;
        this.isActive = isActive;
        this.isToStockControl = isToStockControl;
        this.purchaseCost = purchaseCost;
        this.currentSalePrice = currentSalePrice;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.location = location;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Product(final String reference,
                   final String name,
                   final UnitType unit,
                   final Brand brands,
                   final Purveyor purveyor,
                   final Boolean isActive,
                   final Boolean isToStockControl,
                   final BigDecimal purchaseCost,
                   final BigDecimal currentSalePrice,
                   final Short currentStock,
                   final Short minStock,
                   final String location
                   ) {
        this(null,
                reference,
                name,
                unit,
                brands,
                purveyor,
                isActive,
                isToStockControl,
                purchaseCost,
                currentSalePrice,
                currentStock,
                minStock,
                location,
                null,
                null);
    }


    public Long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public String getName() {
        return name;
    }

    public UnitType getUnit() {
        return unit;
    }

    public Brand getBrands() {
        return brands;
    }

    public Purveyor getPurveyor() {
        return purveyor;
    }

    @Override
    public void activate() {
        isActive = true;
    }

    @Override
    public void inactivate() {
        isActive = false;
    }

    public Boolean isActive() {
        return isActive;
    }

    public Boolean isInventoryControl() {
        return isToStockControl;
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    public BigDecimal getCurrentSalePrice() {
        return currentSalePrice;
    }

    public Short getCurrentStock() {
        return currentStock;
    }

    public Short getMinStock() {
        return minStock;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public enum UnitType {
        PIECE,
        PAIR,
        KIT,
        BOTTLE
    }

    @PrePersist
    private void prePersist(){
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.of(createdOn.toLocalDate(), createdOn.toLocalTime());
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedOn = LocalDateTime.of(createdOn.toLocalDate(), createdOn.toLocalTime());
    }
}

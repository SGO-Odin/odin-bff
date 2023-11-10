package com.odin.odinbff.model.product;

import com.odin.odinbff.model.HasLongId;
import com.odin.odinbff.model.audit.HistoryLoggable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = Brand.Constants.CONSTRAINT_UK_BRAND_NAME, columnNames = {"name"})
})
public final class Brand extends HistoryLoggable<Brand> implements HasLongId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Length(min = Constants.VALIDATION_NAME_MIN_LENGTH, max = Constants.VALIDATION_NAME_MAX_LENGTH)
    @Column(nullable = false, length = Constants.VALIDATION_NAME_MAX_LENGTH)
    private final String name;

    @Column(nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdOn = null;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedOn = null;
    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private Brand() {
        this.name = null;
        this.id = null;
        this.createdOn = null;
        this.updatedOn = null;
    }

    public Brand(Long id, final String name) {
        this.name = name;
        this.id = id;
    }

    public Brand(final String name) {
        this(null, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean isActive() {
        return isActive;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void inactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    @Override
    protected Set<String> attrToUpdateLog() {
        return Set.of(
                "name",
                "isActive"
        );
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }

    @PrePersist
    private void prePersist() {
        this.updatedOn = LocalDateTime.now();
        this.createdOn = updatedOn;
    }

    static final class Constants {
        public final static String CONSTRAINT_UK_BRAND_NAME = "uk_brand_name";
        public final static short VALIDATION_NAME_MAX_LENGTH = 255;
        public final static short VALIDATION_NAME_MIN_LENGTH = 1;
    }

}

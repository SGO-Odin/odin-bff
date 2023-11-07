package com.odin.odinbff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = Constraints.Brand.UK_BRAND_NAME, columnNames = {"name"})
})
public final class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(nullable = false, length = Validations.Name.MAX_LENGTH, unique = true, updatable = false)
    @Length(min = Validations.Name.MIN_LENGTH, max = Validations.Name.MAX_LENGTH)
    private final String name;

    @Column(nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(nullable = false)
    private final LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(nullable = false)
    private final LocalDateTime updatedOn;
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
        this.createdOn = LocalDateTime.now();
        this.updatedOn = createdOn;
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

    public static final class Validations {
        public static final class Name {
            public static final short MAX_LENGTH = 255;
            public static final short MIN_LENGTH = 1;
        }
    }
}

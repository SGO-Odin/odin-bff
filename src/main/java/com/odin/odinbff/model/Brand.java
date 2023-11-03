package com.odin.odinbff.model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
public final class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(nullable = false, length = Validations.Name.MAX_LENGTH, unique = true)
    @Length(min = Validations.Name.MIN_LENGTH, max = Validations.Name.MAX_LENGTH)
    private final String name;

    /**
     * Don't use. Requires by JPA.
     */
    @Deprecated
    private Brand() {
        this(null, null);
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

    public static final class Validations {
        public static final class Name {
            public static final short MAX_LENGTH = 255;
            public static final short MIN_LENGTH = 1;
        }
    }
}

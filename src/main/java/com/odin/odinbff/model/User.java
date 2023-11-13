package com.odin.odinbff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
public final class User implements UserDetails {


    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private final UUID id;

    @Column(nullable = false, length = Constants.VALIDATION_NAME_MAX_LENGTH)
    @Length(min = Constants.VALIDATION_NAME_MIN_LENGTH, max = Constants.VALIDATION_NAME_MAX_LENGTH)
    private final String username;

    @Column(nullable = false, length = Constants.VALIDATION_PASSWORD_MAX_LENGTH)
    @Length(min = Constants.VALIDATION_PASSWORD_MIN_LENGTH, max = Constants.VALIDATION_PASSWORD_MAX_LENGTH)
    @Pattern(regexp = Constants.VALIDATION_PASSWORD_PATTERN)
    private final String password;

    private final Roles role;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private User() {
        this(null,null, null, null);
    }

    private User(final UUID id, @NotNull final String username, @NotNull final String password, Roles role) {
        this.id = id;
        this.username = username;
        this.password = password == null ? null : new BCryptPasswordEncoder().encode(password);
        this.role = role;
    }

    public User(@NotNull final String username, @NotNull final String password, @NotNull Roles role) {
        this(null, username, password, role);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public enum Roles {
        MANAGER,
        ATTENDANT
    }

    public final static class Constants {

        public final static byte VALIDATION_NAME_MAX_LENGTH = 32;
        public final static byte VALIDATION_NAME_MIN_LENGTH = 6;

        public final static byte VALIDATION_PASSWORD_MAX_LENGTH = 127;
        public final static byte VALIDATION_PASSWORD_MIN_LENGTH = 8;

        public final static String VALIDATION_PASSWORD_PATTERN =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{" + VALIDATION_PASSWORD_MIN_LENGTH + "," + VALIDATION_PASSWORD_MAX_LENGTH + "}$";
    }

}

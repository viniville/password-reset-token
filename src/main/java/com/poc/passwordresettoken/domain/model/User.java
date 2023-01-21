package com.poc.passwordresettoken.domain.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.beans.Transient;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usersys")
public class User implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotBlank
    @Size(min = 10, max = 255)
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @Column(nullable = false, updatable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @Past
    private LocalDate birthDate;
    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private OffsetDateTime creationDate;

    @Version
    @UpdateTimestamp
    @Column(name = "modification_date")
    private OffsetDateTime modificationDate;

    //@TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(name = "deactivation_date", updatable = false)
    private OffsetDateTime deactivationDate;

    @Transient
    public boolean isActive() {
        return this.deactivationDate == null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
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
        return this.isActive();
    }
}

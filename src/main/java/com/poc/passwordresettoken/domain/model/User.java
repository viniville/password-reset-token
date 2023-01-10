package com.poc.passwordresettoken.domain.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usersys")
public class User {

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
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @Past
    private LocalDate birthDate;
    @CreationTimestamp
    @Column(name = "creation_date")
    private OffsetDateTime creationDate;

    @Version
    @UpdateTimestamp
    @Column(name = "modification_date")
    private OffsetDateTime modificationDate;

    //@TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(name = "deactivation_date")
    private OffsetDateTime deactivationDate;

    @Transient
    public boolean isActive() {
        return this.deactivationDate == null;
    }

}

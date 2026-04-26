package com.draco_dr.api_auth.features.role.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Role {

    // -------------------------------------------------------------------------
    // Atributs
    // -------------------------------------------------------------------------
    private final UUID id;
    private String name;
    private String description;
    private final Set<String> permissions;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    // -------------------------------------------------------------------------
    // Factory methods
    // -------------------------------------------------------------------------

    /**
     * Creates a new Role with a generated UUID and current timestamps.
     * Used when creating a role for the first time.
     */
    public static Role create(String name, String description) {
        LocalDateTime now = LocalDateTime.now();
        return new Role(
                UUID.randomUUID(),
                normalizeName(name),
                description,
                new HashSet<>(),
                true,
                now,
                now
        );
    }

    /**
     * Reconstitutes a Role from persisted data.
     * Used by the persistence adapter to rebuild the domain object.
     */
    public static Role reconstitute(UUID id,
                                    String name,
                                    String description,
                                    Set<String> permissions,
                                    boolean active,
                                    LocalDateTime createdAt,
                                    LocalDateTime updatedAt) {
        return new Role(id, name, description, permissions, active, createdAt, updatedAt);
    }

    // -------------------------------------------------------------------------
    // Business behavior
    // -------------------------------------------------------------------------

    public void updateInfo(String name, String description) {
        if (name != null && !name.isBlank()) {
            this.name = normalizeName(name);
        }
        if (description != null) {
            this.description = description;
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void addPermission(String permissionCode) {
        if (permissionCode == null || permissionCode.isBlank()) {
            throw new IllegalArgumentException("Permission code must not be blank");
        }
        this.permissions.add(permissionCode.toUpperCase());
        this.updatedAt = LocalDateTime.now();
    }

    public void removePermission(String permissionCode) {
        this.permissions.remove(permissionCode.toUpperCase());
        this.updatedAt = LocalDateTime.now();
    }

    public void replacePermissions(Set<String> newPermissions) {
        this.permissions.clear();
        if (newPermissions != null) {
            newPermissions.forEach(p -> this.permissions.add(p.toUpperCase()));
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        this.active = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean hasPermission(String permissionCode) {
        return this.permissions.contains(permissionCode.toUpperCase());
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    private static String normalizeName(String name) {
        return name.trim().toUpperCase();
    }
}

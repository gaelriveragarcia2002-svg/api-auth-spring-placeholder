package com.draco_dr.api_auth.features.permission.domain;
import com.draco_dr.api_auth.core.common.domain.DomainEntity;
import com.draco_dr.api_auth.core.common.utils.SlugUtils;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Objects;

public class Permission implements DomainEntity {

    private UUID id;
    private String name;
    private String slug;
    private String description;

    // createdAt es final porque no debe cambiar una vez creada la instancia
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor completo
    private Permission(UUID id, String name, String slug, String description) {
        this.id = id != null ? id : UUID.randomUUID();
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor vacío (necesario si alguna librería de serialización lo requiere)
    public Permission() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // --- Getters y Setters ---
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Méthod para actualizar la fecha de modificación manualmente
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    //* Metodos estaticos de los permisos.

    // Metodo factory para crear un nuevo permiso.
    public static Permission create(String name, String description){
        return new Permission(UUID.randomUUID(), name, SlugUtils.toSlug(name), description);
    }

    // Metodo factory para reconstruir desde la Base De Datos.
    public  static Permission reconstitute(UUID id, String name, String slug, String description){
        return  new Permission(id, name, slug, description);
    }

    // --- Equals y HashCode (Basado en el Slug, tu llave de negocio) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(slug, that.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

package com.draco_dr.api_auth.features.permission.infrastructure.adapters.out.mappers;
import com.draco_dr.api_auth.features.permission.domain.Permission;
import com.draco_dr.api_auth.features.permission.infrastructure.adapters.out.entities.PermissionEntity;
import org.springframework.stereotype.Component;

@Component
public class PermissionEntityMapper {

    public PermissionEntity toEntity(Permission permission) {
        return PermissionEntity.builder()
                .id(permission.getId())
                .name(permission.getName())
                .slug(permission.getSlug())
                .description(permission.getDescription())
                .createdAt(permission.getCreatedAt())
                .updatedAt(permission.getUpdatedAt())
                .build();
    }

    public Permission toDomain(PermissionEntity entity) {
        return Permission.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getSlug(),
                entity.getDescription()
        );
    }
}

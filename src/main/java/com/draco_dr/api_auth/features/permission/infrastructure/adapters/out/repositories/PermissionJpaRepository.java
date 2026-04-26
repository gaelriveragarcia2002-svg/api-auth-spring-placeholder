package com.draco_dr.api_auth.features.permission.infrastructure.adapters.out.repositories;

import com.draco_dr.api_auth.features.permission.infrastructure.adapters.out.entities.PermissionEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PermissionJpaRepository extends JpaRepository<PermissionEntity, UUID> {
    boolean existsByName(String v);
    boolean existsById(@NonNull UUID id);
}

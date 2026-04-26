package com.draco_dr.api_auth.features.permission.application.ports.out;
import com.draco_dr.api_auth.features.permission.domain.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionRepositoryPort {
    List<Permission> read();
    Page<Permission> readPaginated(Pageable pageable);
    Permission save(Permission v);
    Permission update(Permission v);
    Optional<Permission> delete(UUID v);
    Optional<Permission> findById(UUID v);
    boolean existByName(String v);
    boolean existById(UUID v);
}

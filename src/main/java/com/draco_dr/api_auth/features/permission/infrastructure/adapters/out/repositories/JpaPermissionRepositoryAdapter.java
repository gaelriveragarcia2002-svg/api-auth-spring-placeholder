package com.draco_dr.api_auth.features.permission.infrastructure.adapters.out.repositories;
import com.draco_dr.api_auth.features.permission.application.ports.out.PermissionRepositoryPort;
import com.draco_dr.api_auth.features.permission.domain.Permission;
import com.draco_dr.api_auth.features.permission.infrastructure.adapters.out.entities.PermissionEntity;
import com.draco_dr.api_auth.features.permission.infrastructure.adapters.out.mappers.PermissionEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaPermissionRepositoryAdapter implements PermissionRepositoryPort {

    // Inyeccion de dependencias.
    private final PermissionJpaRepository permissionJpaRepository;
    private final PermissionEntityMapper permissionEntityMapper;

    // Metodos del repositorio.
    @Override
    public List<Permission> read() {
        return permissionJpaRepository.findAll()
                .stream()
                .map(permissionEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Page<Permission> readPaginated(Pageable pageable) {
        return permissionJpaRepository.findAll(pageable)
                .map(permissionEntityMapper::toDomain);
    }

    @Override
    public Permission save(Permission v) {
        return permissionEntityMapper.toDomain(
                permissionJpaRepository.save(permissionEntityMapper.toNewEntity(v))
        );
    }

    @Override
    public Permission update(Permission v) {
        return permissionEntityMapper.toDomain(
                permissionJpaRepository.save(permissionEntityMapper.toEntity(v))
        );
    }

    @Override
    public Optional<Permission> delete(UUID id) {
        // 1. Buscamos el permiso
        Optional<PermissionEntity> entity = permissionJpaRepository.findById(id);

        // 2. Si existe, lo borramos y retornamos la respuesta
        if (entity.isPresent()) {
            permissionJpaRepository.deleteById(id);
            return Optional.of(permissionEntityMapper.toDomain(entity.get()));
        }

        // 3. Si no existe, retornamos un Optional vacío
        return Optional.empty();
    }

    @Override
    public Optional<Permission> findById(UUID v) {
        return permissionJpaRepository.findById(v)
                .map(permissionEntityMapper::toDomain);
    }

    @Override
    public boolean existByName(String v) {
        return permissionJpaRepository.existsByName(v);
    }

    @Override
    public boolean existById(UUID v) {
        return permissionJpaRepository.existsById(v);
    }

}

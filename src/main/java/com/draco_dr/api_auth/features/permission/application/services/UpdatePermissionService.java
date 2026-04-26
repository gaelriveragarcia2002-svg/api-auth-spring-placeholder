package com.draco_dr.api_auth.features.permission.application.services;
import com.draco_dr.api_auth.core.common.utils.SlugUtils;
import com.draco_dr.api_auth.features.permission.application.dto.request.UpdatePermissionRequest;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;
import com.draco_dr.api_auth.features.permission.application.mapper.PermissionDtoMapper;
import com.draco_dr.api_auth.features.permission.application.ports.in.UpdatePermissionUseCase;
import com.draco_dr.api_auth.features.permission.application.ports.out.PermissionRepositoryPort;
import com.draco_dr.api_auth.features.permission.domain.Permission;
import com.draco_dr.api_auth.features.permission.domain.exception.PermissionErrorCode;
import com.draco_dr.api_auth.features.permission.domain.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdatePermissionService implements UpdatePermissionUseCase {

    // * Atributos del servicio.
    private final PermissionRepositoryPort permissionRepositoryPort;
    private final PermissionDtoMapper permissionDtoMapper;

    // * Inyeccion De Dependencias.
    public UpdatePermissionService(PermissionRepositoryPort permissionRepositoryPort, PermissionDtoMapper permissionDtoMapper){
        this.permissionRepositoryPort = permissionRepositoryPort;
        this.permissionDtoMapper = permissionDtoMapper;
    }

    @Override
    public PermissionResponse execute(UpdatePermissionRequest v) {
        log.info("Validando si el permiso ya existe: {}", v.name());

        if (!permissionRepositoryPort.existById(v.id())) {
            throw new PermissionException(PermissionErrorCode.PERMISSION_NOT_FOUND, v.name());
        }

        String slug = (v.slug() != null && !v.slug().isBlank()) ? v.slug() : SlugUtils.toSlug(v.name());
        Permission permission = Permission.reconstitute(v.id(), v.name(), slug, v.description());
        log.info("Actualizando permiso: {}", v.id());

        Permission updated = permissionRepositoryPort.update(permission);

        return permissionDtoMapper.toResponse(updated);
    }
}

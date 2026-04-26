package com.draco_dr.api_auth.features.permission.application.services;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;
import com.draco_dr.api_auth.features.permission.application.mapper.PermissionDtoMapper;
import com.draco_dr.api_auth.features.permission.application.ports.in.DeletePermissionUseCase;
import com.draco_dr.api_auth.features.permission.application.ports.out.PermissionRepositoryPort;
import com.draco_dr.api_auth.features.permission.domain.exception.PermissionErrorCode;
import com.draco_dr.api_auth.features.permission.domain.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Slf4j
@Service
public class DeletePermissionService implements DeletePermissionUseCase{

    // * Atributos del servicio.
    private final PermissionRepositoryPort permissionRepositoryPort;
    private final PermissionDtoMapper permissionDtoMapper;

    // * Inyeccion De Dependencias.
    public DeletePermissionService(PermissionRepositoryPort permissionRepositoryPort, PermissionDtoMapper permissionDtoMapper){
        this.permissionRepositoryPort = permissionRepositoryPort;
        this.permissionDtoMapper = permissionDtoMapper;
    }

    @Override
    public PermissionResponse execute(UUID v) {
        log.info("Eliminando permiso por id: {}", v);

        return permissionRepositoryPort.delete(v)
                .map(permissionDtoMapper::toResponse)
                .orElseThrow(() -> new PermissionException(PermissionErrorCode.PERMISSION_NOT_FOUND, v));
    }
}

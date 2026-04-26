package com.draco_dr.api_auth.features.permission.application.services;
import com.draco_dr.api_auth.features.permission.application.dto.request.CreatePermissionRequest;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;
import com.draco_dr.api_auth.features.permission.application.mapper.PermissionDtoMapper;
import com.draco_dr.api_auth.features.permission.application.ports.in.CreatePermissionUseCase;
import com.draco_dr.api_auth.features.permission.application.ports.out.PermissionRepositoryPort;
import com.draco_dr.api_auth.features.permission.domain.Permission;
import com.draco_dr.api_auth.features.permission.domain.exception.PermissionErrorCode;
import com.draco_dr.api_auth.features.permission.domain.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreatePermissionService implements CreatePermissionUseCase {

    // * Atributos del servicio.
    private final PermissionRepositoryPort permissionRepositoryPort;
    private final PermissionDtoMapper permissionDtoMapper;

    // * Inyeccion De Dependencias.
    public CreatePermissionService(PermissionRepositoryPort permissionRepositoryPort, PermissionDtoMapper permissionDtoMapper){
        this.permissionRepositoryPort = permissionRepositoryPort;
        this.permissionDtoMapper = permissionDtoMapper;
    }

    // * Metodo de Ejecucion.
    @Override
    public PermissionResponse execute(CreatePermissionRequest v) {
        log.info("Validando si el permiso ya existe: {}", v.name());

        // 1. Llamamos al puerto para verificar existencia (lógica de negocio)
        if (permissionRepositoryPort.existByName(v.name())) {
            throw new PermissionException(PermissionErrorCode.PERMISSION_ALREADY_EXISTS, v.name());
        }

        // 2. Si pasa la validación, procedemos
        log.info("Creando permiso: {}", v.name());
        Permission permission = Permission.create(v.name(), v.description());
        Permission saved = permissionRepositoryPort.save(permission);

        return permissionDtoMapper.toResponse(saved);
    }
}

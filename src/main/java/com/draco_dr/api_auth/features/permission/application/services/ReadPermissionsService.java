package com.draco_dr.api_auth.features.permission.application.services;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;
import com.draco_dr.api_auth.features.permission.application.mapper.PermissionDtoMapper;
import com.draco_dr.api_auth.features.permission.application.ports.in.ReadPermissionsUseCase;
import com.draco_dr.api_auth.features.permission.application.ports.out.PermissionRepositoryPort;
import com.draco_dr.api_auth.features.permission.domain.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
public class ReadPermissionsService implements ReadPermissionsUseCase {

    // * Atributos del servicio.
    private final PermissionRepositoryPort permissionRepositoryPort;
    private final PermissionDtoMapper permissionDtoMapper;

    // * Inyeccion De Dependencias.
    public ReadPermissionsService(PermissionRepositoryPort permissionRepositoryPort, PermissionDtoMapper permissionDtoMapper){
        this.permissionRepositoryPort = permissionRepositoryPort;
        this.permissionDtoMapper = permissionDtoMapper;
    }

    @Override
    public List<PermissionResponse> execute() {
        log.info("Obteniendo permisos: {}");
        List<Permission> readed = permissionRepositoryPort.read();
        log.info("Permisos obtenidos con exito");
        return readed.stream().map(permissionDtoMapper::toResponse).toList();
    }
}

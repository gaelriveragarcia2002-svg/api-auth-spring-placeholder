package com.draco_dr.api_auth.features.permission.application.services;

import com.draco_dr.api_auth.features.permission.application.dto.request.ReadPermissionByIdRequest;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;
import com.draco_dr.api_auth.features.permission.application.mapper.PermissionDtoMapper;
import com.draco_dr.api_auth.features.permission.application.ports.in.ReadPermissionByIdUseCase;
import com.draco_dr.api_auth.features.permission.application.ports.out.PermissionRepositoryPort;
import com.draco_dr.api_auth.features.permission.domain.exception.PermissionErrorCode;
import com.draco_dr.api_auth.features.permission.domain.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
public class ReadPermissionByIdService implements ReadPermissionByIdUseCase {

    private final PermissionRepositoryPort permissionRepositoryPort;
    private final PermissionDtoMapper permissionDtoMapper;

    public ReadPermissionByIdService(PermissionRepositoryPort permissionRepositoryPort, PermissionDtoMapper permissionDtoMapper) {
        this.permissionRepositoryPort = permissionRepositoryPort;
        this.permissionDtoMapper = permissionDtoMapper;
    }

    @Override
    public PermissionResponse execute(UUID v) {
        log.info("Buscando permiso por id: {}", v);

        return permissionRepositoryPort.findById(v)
                .map(permissionDtoMapper::toResponse)
                .orElseThrow(() -> new PermissionException(PermissionErrorCode.PERMISSION_NOT_FOUND, v));

    }
}
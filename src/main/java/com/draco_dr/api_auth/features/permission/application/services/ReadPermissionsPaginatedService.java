package com.draco_dr.api_auth.features.permission.application.services;

import com.draco_dr.api_auth.core.common.infrastructure.dto.PageMeta;
import com.draco_dr.api_auth.core.common.infrastructure.dto.PagedData;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;
import com.draco_dr.api_auth.features.permission.application.mapper.PermissionDtoMapper;
import com.draco_dr.api_auth.features.permission.application.ports.in.ReadPermissionsPaginatedUseCase;
import com.draco_dr.api_auth.features.permission.application.ports.out.PermissionRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadPermissionsPaginatedService implements ReadPermissionsPaginatedUseCase {

    private final PermissionRepositoryPort permissionRepositoryPort;
    private final PermissionDtoMapper permissionDtoMapper;

    @Override
    public PagedData<PermissionResponse> execute(int page, int perPage) {
        log.info("Obteniendo permisos paginados: page={}, perPage={}", page, perPage);
        Page<PermissionResponse> result = permissionRepositoryPort
                .readPaginated(PageRequest.of(page, perPage))
                .map(permissionDtoMapper::toResponse);
        log.info("Permisos paginados obtenidos con exito");
        return new PagedData<>(
                result.getContent(),
                new PageMeta(page, perPage, result.getTotalElements(), result.getTotalPages())
        );
    }
}
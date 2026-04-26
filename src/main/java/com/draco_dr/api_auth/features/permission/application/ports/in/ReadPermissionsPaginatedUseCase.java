package com.draco_dr.api_auth.features.permission.application.ports.in;


import com.draco_dr.api_auth.core.common.application.usecase.PaginatedUseCase;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;

public interface ReadPermissionsPaginatedUseCase extends PaginatedUseCase<PermissionResponse> {}

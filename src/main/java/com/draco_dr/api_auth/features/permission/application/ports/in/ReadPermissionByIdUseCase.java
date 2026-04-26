package com.draco_dr.api_auth.features.permission.application.ports.in;

import com.draco_dr.api_auth.core.common.application.usecase.UseCase;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;

import java.util.UUID;

public interface ReadPermissionByIdUseCase extends UseCase<UUID, PermissionResponse> {
}

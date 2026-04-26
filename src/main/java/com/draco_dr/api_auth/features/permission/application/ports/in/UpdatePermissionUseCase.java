package com.draco_dr.api_auth.features.permission.application.ports.in;

import com.draco_dr.api_auth.core.common.application.usecase.UseCase;
import com.draco_dr.api_auth.features.permission.application.dto.request.UpdatePermissionRequest;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;

public interface UpdatePermissionUseCase extends UseCase<UpdatePermissionRequest, PermissionResponse> {
}

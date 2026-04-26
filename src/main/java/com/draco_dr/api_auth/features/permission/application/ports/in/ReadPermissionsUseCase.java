package com.draco_dr.api_auth.features.permission.application.ports.in;
import com.draco_dr.api_auth.core.common.application.usecase.NoInputUseCase;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;
import java.util.List;

public interface ReadPermissionsUseCase extends NoInputUseCase<List<PermissionResponse>> {}

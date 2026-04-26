package com.draco_dr.api_auth.features.permission.application.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReadPermissionByIdRequest(
        @NotNull
        UUID id
) {
}

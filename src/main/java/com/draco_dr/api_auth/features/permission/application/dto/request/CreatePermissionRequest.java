package com.draco_dr.api_auth.features.permission.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePermissionRequest(
        @NotBlank @Size(min = 2, max = 50)
        String name,
        @NotBlank
        String description
) {
}

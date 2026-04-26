package com.draco_dr.api_auth.features.permission.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record UpdatePermissionRequest(
        @NotNull
        UUID id,
        @NotBlank @Size(min = 2, max = 50)
        String name,
        @NotBlank
        String slug,
        @NotBlank
        String description
) {
}

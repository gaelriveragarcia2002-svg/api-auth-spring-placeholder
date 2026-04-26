package com.draco_dr.api_auth.features.permission.application.dto.request;

import jakarta.validation.constraints.Size;

public record UpdatePermissionBody(
        @Size(min = 2, max = 50)
        String name,
        String slug,
        String description
) {
}
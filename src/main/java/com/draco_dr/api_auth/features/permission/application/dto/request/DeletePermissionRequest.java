package com.draco_dr.api_auth.features.permission.application.dto.request;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record DeletePermissionRequest(
        @NotBlank
        UUID id
) {
}

package com.draco_dr.api_auth.features.permission.application.dto.response;
import java.time.LocalDateTime;
import java.util.UUID;

public record PermissionResponse(
        UUID id,
        String name,
        String slug,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

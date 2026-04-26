package com.draco_dr.api_auth.features.permission.application.mapper;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;
import com.draco_dr.api_auth.features.permission.domain.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionDtoMapper {

    public PermissionResponse toResponse(Permission v){
        return new PermissionResponse(
                v.getId(),
                v.getName(),
                v.getSlug(),
                v.getDescription(),
                v.getCreatedAt(),
                v.getUpdatedAt()
        );
    }
}

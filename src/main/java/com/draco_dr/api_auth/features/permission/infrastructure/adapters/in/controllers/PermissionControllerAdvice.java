package com.draco_dr.api_auth.features.permission.infrastructure.adapters.in.controllers;
import com.draco_dr.api_auth.core.common.infrastructure.dto.ApiResponse;
import com.draco_dr.api_auth.features.permission.domain.exception.PermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = PermissionController.class)
public class PermissionControllerAdvice {

    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<ApiResponse<Void>> handle(PermissionException ex) {
        HttpStatus status = switch (ex.getErrorCode()) {
            case PERMISSION_ALREADY_EXISTS -> HttpStatus.UNPROCESSABLE_ENTITY;
            case PERMISSION_NOT_FOUND      -> HttpStatus.NOT_FOUND;
        };
        return ResponseEntity
                .status(status)
                .body(ApiResponse.error(status.value(), ex.getMessage(), null));
    }
}

package com.draco_dr.api_auth.features.role.domain.exception;

public class RoleAlreadyExistsException extends RuntimeException {

    public RoleAlreadyExistsException(String name) {
        super("Role already exists with name: " + name);
    }
}

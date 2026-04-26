package com.draco_dr.api_auth.features.permission.domain.exception;

public class PermissionException extends RuntimeException{

    private final PermissionErrorCode permissionErrorCode;

    public PermissionException(PermissionErrorCode errorCode, Object... args){
        super(errorCode.format(args));
        this.permissionErrorCode = errorCode;
    }

    public PermissionErrorCode getErrorCode(){
        return permissionErrorCode;
    }
}

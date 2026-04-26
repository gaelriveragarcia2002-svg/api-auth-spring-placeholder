package com.draco_dr.api_auth.features.permission.domain.exception;

public enum PermissionErrorCode {

    // Definicion de ENUMS.
    PERMISSION_ALREADY_EXISTS("El permiso '%s' ya existe"),
    PERMISSION_NOT_FOUND("Permiso '%s' no encontrado");

    private final  String messageTemplate;

    PermissionErrorCode(String messageTemplate){
        this.messageTemplate = messageTemplate;
    }

    public String format(Object... args){
        return String.format(messageTemplate, args);
    }

}

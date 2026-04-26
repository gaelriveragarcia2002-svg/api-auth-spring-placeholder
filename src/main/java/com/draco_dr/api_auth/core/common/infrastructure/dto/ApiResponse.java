package com.draco_dr.api_auth.core.common.infrastructure.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        int status,
        boolean success,
        String message,
        T data,
        Map<String, String> errors,
        Instant timestamp
) {
    public static <T> ApiResponse<T> ok(T data, String message) {
        return new ApiResponse<>(200, true, message, data, null, Instant.now());
    }

    public static <T> ApiResponse<Map<String, T>> ok(String key, T data, String message) {
        return new ApiResponse<>(200, true, message, Map.of(key, data), null, Instant.now());
    }

    public static <T> ApiResponse<T> created(T data, String message) {
        return new ApiResponse<>(201, true, message, data, null, Instant.now());
    }

    public static <T> ApiResponse<Map<String, T>> created(String key, T data, String message) {
        return new ApiResponse<>(201, true, message, Map.of(key, data), null, Instant.now());
    }

    public static <T> ApiResponse<Map<String, Object>> paginated(String key, PagedData<T> data, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(key, data.content());
        body.put("meta", data.meta());
        return new ApiResponse<>(200, true, message, body, null, Instant.now());
    }

    public static ApiResponse<Void> error(int status, String message, Map<String, String> errors) {
        return new ApiResponse<>(status, false, message, null, errors, Instant.now());
    }
}
package com.draco_dr.api_auth.features.permission.infrastructure.adapters.in.controllers;
import com.draco_dr.api_auth.core.common.infrastructure.dto.ApiResponse;
import com.draco_dr.api_auth.core.common.infrastructure.dto.PagedData;
import com.draco_dr.api_auth.features.permission.application.dto.request.CreatePermissionRequest;
import com.draco_dr.api_auth.features.permission.application.dto.request.UpdatePermissionBody;
import com.draco_dr.api_auth.features.permission.application.dto.request.UpdatePermissionRequest;
import com.draco_dr.api_auth.features.permission.application.dto.response.PermissionResponse;
import com.draco_dr.api_auth.features.permission.application.ports.in.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {

    // * Inyeccion de dependencias.
    private final CreatePermissionUseCase createPermissionUseCase;
    private final ReadPermissionsUseCase readPermissionsUseCase;
    private final ReadPermissionsPaginatedUseCase readPermissionsPaginatedUseCase;
    private final ReadPermissionByIdUseCase readPermissionByIdUseCase;
    private final UpdatePermissionUseCase updatePermissionUseCase;
    private final  DeletePermissionUseCase deletePermissionUseCase;

    // * Constanes del servicio.
    private static final String plural_label = "permisos";
    private static final String singular_label = "permiso";

    //* Metodos REST del permiso.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Map<String, PermissionResponse>> create(@RequestBody @Valid CreatePermissionRequest req) {
        PermissionResponse response = createPermissionUseCase.execute(req);
        return ApiResponse.created(PermissionController.singular_label, response, "Permission created successfully");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> read(
            @RequestParam(required = false, name = "page") Integer page,
            @RequestParam(required = false, name = "per_page") Integer perPage
    ) {
        if (page != null && perPage != null) {
            PagedData<PermissionResponse> pagedData = readPermissionsPaginatedUseCase.execute(page, perPage);
            return ApiResponse.paginated(PermissionController.plural_label, pagedData, "Permissions fetched successfully");
        }
        List<PermissionResponse> response = readPermissionsUseCase.execute();
        return ApiResponse.ok(PermissionController.plural_label, response, "Permissions fetched successfully");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Map<String, PermissionResponse>> readById(@PathVariable UUID id) {
        PermissionResponse response = readPermissionByIdUseCase.execute(id);
        return ApiResponse.ok(PermissionController.singular_label, response, "Permission fetched successfully");
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Map<String, PermissionResponse>> update(@PathVariable UUID id, @RequestBody @Valid UpdatePermissionBody body) {
        UpdatePermissionRequest command = new UpdatePermissionRequest(id, body.name(), body.slug(), body.description());
        PermissionResponse response = updatePermissionUseCase.execute(command);
        return ApiResponse.ok(PermissionController.singular_label, response, "Permission updated successfully");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Map<String, PermissionResponse>> deleteById(@PathVariable UUID id) {
        PermissionResponse response = deletePermissionUseCase.execute(id);
        return  ApiResponse.ok(PermissionController.singular_label, response, "Permission deleted successfully");
    }
}

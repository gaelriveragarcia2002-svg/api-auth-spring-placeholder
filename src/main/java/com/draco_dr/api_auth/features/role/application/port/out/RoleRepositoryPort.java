package com.draco_dr.api_auth.features.role.application.port.out;

import com.draco_dr.api_auth.features.role.domain.model.Role;

public interface RoleRepositoryPort {
    Role save(Role role);
    boolean existByName(String name);
}

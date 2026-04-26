package com.draco_dr.api_auth.features.role.infrastructure.adapter.out.persistence.mapper;

import com.draco_dr.api_auth.features.role.domain.model.Role;
import com.draco_dr.api_auth.features.role.infrastructure.adapter.out.persistence.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component()
public class RolePersistenceMapper {

    public RoleEntity toEntity(Role domain) {
        RoleEntity entity = new RoleEntity();
        entity.setID(domain.getId());
        entity.setName(domain.getName());
        return entity;
    }

    public Role toDomain(RoleEntity entity) {
        return Role.reconstitute(
                entity.getID(),
                entity.getName(),
                null,
                new HashSet<>(),
                true,
                null,
                null
        );
    }

}

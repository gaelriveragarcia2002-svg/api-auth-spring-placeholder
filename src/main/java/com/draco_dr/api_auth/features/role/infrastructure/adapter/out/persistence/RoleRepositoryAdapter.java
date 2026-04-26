package com.draco_dr.api_auth.features.role.infrastructure.adapter.out.persistence;
import com.draco_dr.api_auth.features.role.application.port.out.RoleRepositoryPort;
import com.draco_dr.api_auth.features.role.domain.model.Role;
import com.draco_dr.api_auth.features.role.infrastructure.adapter.out.persistence.mapper.RolePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final RoleRepository roleRepository;
    private final RolePersistenceMapper rolePersistenceMapper;

    @Override
    public Role save(Role role) {
        RoleEntity entity = new RoleEntity();
        entity.setID(role.getId());
        entity.setName(role.getName());
        roleRepository.save(entity);
        return rolePersistenceMapper.toDomain(entity);
    }

    @Override
    public boolean existByName(String name) {
        return roleRepository.existsByName(name);
    }
}

package com.draco_dr.api_auth.features.role.application.usecase;
import com.draco_dr.api_auth.features.role.application.port.in.CreateRoleUseCase;
import com.draco_dr.api_auth.features.role.application.port.out.RoleRepositoryPort;
import com.draco_dr.api_auth.features.role.domain.model.Role;
import com.draco_dr.api_auth.features.role.domain.model.command.CreateRoleCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

    private  final RoleRepositoryPort roleRepositoryPort;

    @Override
    public Role execute(CreateRoleCommand v) {
        String normalized = v.name().trim().toUpperCase();

        if(roleRepositoryPort.existByName(normalized)){
            throw  new RuntimeException("Ya existe un rol con el nombre: "+ normalized);
        }
        Role newRole = Role.create(v.name(), v.description());

        roleRepositoryPort.save(newRole);

        return newRole;
    }
}

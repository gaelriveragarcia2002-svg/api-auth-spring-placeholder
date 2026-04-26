package com.draco_dr.api_auth.features.role.application.port.in;


import com.draco_dr.api_auth.core.common.application.usecase.UseCase;
import com.draco_dr.api_auth.features.role.domain.model.Role;
import com.draco_dr.api_auth.features.role.domain.model.command.CreateRoleCommand;

public interface CreateRoleUseCase extends UseCase<CreateRoleCommand, Role> {

    @Override
    Role execute(CreateRoleCommand v);
}

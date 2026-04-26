package com.draco_dr.api_auth.core.common.application.usecase;

import com.draco_dr.api_auth.core.common.infrastructure.dto.PagedData;

public interface PaginatedUseCase<N> {
    PagedData<N> execute(int page, int perPage);
}
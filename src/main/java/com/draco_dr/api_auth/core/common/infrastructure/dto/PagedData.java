package com.draco_dr.api_auth.core.common.infrastructure.dto;

import java.util.List;

public record PagedData<T>(List<T> content, PageMeta meta) {}

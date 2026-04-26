package com.draco_dr.api_auth.core.common.application.usecase;

public interface UseCase<T, N> {
    N execute(T v);
}
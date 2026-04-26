package com.draco_dr.api_auth.core.common.domain;
import java.time.LocalDateTime;
import java.util.UUID;

public interface DomainEntity {
    UUID getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    void updateTimestamp();
}
-- =============================================================
-- V1: Create roles and role_permissions tables
-- =============================================================

CREATE TABLE roles (
    id          UUID            PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL UNIQUE,
    description VARCHAR(500),
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP       NOT NULL DEFAULT NOW()
);

CREATE TABLE role_permissions (
    role_id         UUID            NOT NULL,
    permission_code VARCHAR(100)    NOT NULL,

    PRIMARY KEY (role_id, permission_code),
    CONSTRAINT fk_role_permissions_role
        FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Index for fast permission lookups by role
CREATE INDEX idx_role_permissions_role_id ON role_permissions(role_id);

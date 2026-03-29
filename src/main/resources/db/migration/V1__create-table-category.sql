CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE category (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    description VARCHAR(30) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    blocked_at TIMESTAMP,
    updated_at TIMESTAMP NOT NULL
);
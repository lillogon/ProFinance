CREATE TABLE institution (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    description VARCHAR(200) NOT NULL,
    is_default BOOLEAN NOT NULL DEFAULT FALSE,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    blocked_at TIMESTAMP,
    updated_at TIMESTAMP NOT NULL
);
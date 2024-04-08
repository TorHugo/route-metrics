CREATE TABLE account_tb(
    account_id uuid primary key,
    name text not null,
    email text not null,
    password text not null ,
    admin boolean not null,
    active boolean not null,
    last_access timestamp not null,
    created_at timestamp not null,
    updated_at timestamp
);

CREATE TABLE forget_password_tb(
    forget_password_id UUID PRIMARY KEY,
    account_id UUID NOT NULL,
    hash_code VARCHAR(6) NOT NULL,
    expiration_at TIMESTAMP NOT NULL,
    active boolean NOT NULL,
    created_at timestamp NOT NULL
);

CREATE INDEX idx_forget_password_hash_active
ON forget_password_tb (hash_code, active);
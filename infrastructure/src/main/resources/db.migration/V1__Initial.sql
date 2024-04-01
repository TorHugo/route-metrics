CREATE TABLE account_tb(
    account_id uuid primary key,
    name text not null,
    email text not null,
    password text not null ,
    admin boolean not null,
    active boolean not null,
    last_access timestamp not null,
    created_at timestamp not null,
    updated_at timestamp not null
);
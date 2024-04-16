-- TODO: BUGFIX flyway
DROP TABLE IF EXISTS account_tb;
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

CREATE INDEX idx_account_email
ON account_tb (email);

DROP TABLE IF EXISTS forget_password_tb;
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

DROP TABLE IF EXISTS route_tb;
CREATE TABLE route_tb(
     route_id UUID PRIMARY KEY,
     account_id UUID NOT NULL,
     name TEXT,
     status TEXT NOT NULL,
     start_latitude NUMERIC NOT NULL,
     start_longitude NUMERIC NOT NULL,
     start_time TIMESTAMP NOT NULL,
     active BOOLEAN NOT NULL,
     created_at TIMESTAMP NOT NULL,
     updated_at TIMESTAMP
);

CREATE INDEX idx_route_tb_account_id
ON route_tb (account_id);

CREATE INDEX idx_route_tb_route_id_status
ON route_tb (route_id, status);

DROP TABLE IF EXISTS position_tb;
CREATE TABLE position_tb(
    position_id UUID PRIMARY KEY,
    route_id UUID NOT NULL,
    last_latitude NUMERIC NOT NULL,
    last_longitude NUMERIC NOT NULL,
    last_time TIMESTAMP NOT NULL,
    max_velocity NUMERIC NOT NULL,
    min_velocity NUMERIC NOT NULL,
    distance NUMERIC NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_position_tb_route_id
ON position_tb (route_id);

DROP TABLE IF EXISTS template_tb;
CREATE TABLE template_tb(
    template_id uuid primary key,
    process TEXT NOT NULL,
    subject TEXT NOT NULL,
    introduction TEXT NOT NULL,
    body TEXT NOT NULL,
    conclusion TEXT NOT NULL,
    signature TEXT NOT NULL,
    active BOOLEAN NOT NULL,
    email BOOLEAN NOT NULL,
    whatsapp BOOLEAN NOT NULL,
    sms BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

DROP TABLE IF EXISTS bucket_tb;
CREATE TABLE bucket_tb(
      bucket_id UUID PRIMARY KEY,
      process TEXT NOT NULL,
      application TEXT NOT NULL,
      identifier TEXT,
      error TEXT NOT NULL,
      created_at TIMESTAMP NOT NULL
);
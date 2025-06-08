CREATE DATABASE hotel;
CREATE USER eu_user WITH ENCRYPTED PASSWORD 'postgres';
ALTER DATABASE hotel OWNER TO eu_user;

GRANT ALL PRIVILEGES ON DATABASE hotel TO eu_user;

-- grant permissions for guest user
CREATE USER eu_guest WITH ENCRYPTED PASSWORD 'postgres';
GRANT CONNECT ON DATABASE hotel TO eu_guest;

-- \connect hotel;
-- CREATE SCHEMA IF NOT EXISTS hotel AUTHORIZATION eu_user;
-- CREATE SCHEMA IF NOT EXISTS customer AUTHORIZATION eu_user;
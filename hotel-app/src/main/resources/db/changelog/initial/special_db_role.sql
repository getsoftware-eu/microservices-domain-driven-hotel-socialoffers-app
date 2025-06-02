-- create schema 'customer'
CREATE SCHEMA IF NOT EXISTS customer;

-- create own users
CREATE ROLE eu_user_role;
CREATE ROLE eu_admin_role;

CREATE USER eu_user with PASSWORD 'querty';
CREATE USER eu_admin with PASSWORD 'querty';

REVOKE CREATE ON SCHEMA public FROM public;
REVOKE ALL ON DATABASE hotel FROM public;

GRANT CONNECT ON DATABASE hotel to eu_user_role;
GRANT CONNECT ON DATABASE hotel to eu_admin_role;

GRANT USAGE ON SCHEMA public TO eu_user;
GRANT USAGE ON SCHEMA public TO eu_admin_role;
GRANT USAGE ON SCHEMA public TO eu_user_role;

GRANT CREATE ON SCHEMA public TO eu_admin_role;
GRANT CREATE ON DATABASE hotel TO eu_admin_role;

GRANT eu_user_role to eu_user
GRANT eu_admin_role to eu_admin

-- test usage ----------------------------------------------

SELECT grantee, privilege_type
FROM hotel.role_table_grants
WHERE table_name = 'hotel';

select * from pg_roles;

-- -------------------------
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE
    public.hotel,
    public.payment,
    public.rusKurs,
    public.postgres
    TO eu_user_role

GRANT SELECT, INSERT, UPDATE, DELETE, REFERENCES, TRIGGER ON ALL TABLES
    IN SCHEMA public
    to eu_admin_role;
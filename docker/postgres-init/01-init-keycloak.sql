CREATE DATABASE keycloak;
CREATE USER keycloak WITH ENCRYPTED PASSWORD 'keycloak';
ALTER DATABASE keycloak OWNER TO keycloak;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak;


-- eu: check user in db
-- docker exec -it postgres_db psql -U postgres

-- \du
-- \l

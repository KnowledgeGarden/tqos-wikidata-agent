-- Run as superuser.
-- IFF the graph is on a different database cluster than
--   the topic map, THEN
-- Run this before graph.sql 
-- Primary roles
CREATE ROLE tq_users;    -- full access to user information
CREATE ROLE tq_users_ro; -- read-only access user information
CREATE ROLE tq_proxy;    -- full access to proxy information
CREATE ROLE tq_proxy_ro; -- read-only access to proxy information

-- CREATE USER tq_admin PASSWORD 'md52c7c554fad386563506b43905bceb7d6'  -- full access
CREATE USER tq_admin PASSWORD 'tq-admin-pwd'  -- full access
    CREATEDB
    NOINHERIT IN ROLE tq_users, tq_proxy;
-- GRANT CREATE ON TABLESPACE tq_space TO tq_admin;

-- CREATE USER tq_user PASSWORD 'md50c6d478265233f1cc3ff062c7e5ef382'  -- limited access
CREATE USER tq_user PASSWORD 'tq-user-pwd'  -- limited access
    NOINHERIT IN ROLE tq_users_ro, tq_proxy_ro;

CREATE EXTENSION IF NOT EXISTS pgcrypto;


-- Switch to the tq_admin user to create the database for TQ objects.
SET ROLE tq_admin;
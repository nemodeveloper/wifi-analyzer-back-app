--liquibase formatted sql

--changeset simanov-an:create-db-changelog-1
--comment Create database
CREATE TABLE PRIVILEGES
(
    ID VARCHAR (36),
    NAME VARCHAR (64),
    DESCRIPTION VARCHAR (256),
    PRIMARY KEY(ID)
);

CREATE TABLE ROLES
(
    ID VARCHAR (36),
    NAME VARCHAR (64),
    DESCRIPTION VARCHAR (256),
    PRIMARY KEY(ID)
);

CREATE TABLE ROLES_PRIVILEGES
(
    ROLE_ID VARCHAR (36),
    PRIVILEGE_ID VARCHAR (36),
    PRIMARY KEY(ROLE_ID, PRIVILEGE_ID)
);

CREATE TABLE USERS
(
    ID VARCHAR (36),
    LOGIN VARCHAR (64),
    PASSWORD VARCHAR (256),
    EMAIL VARCHAR (256),
    ENABLED VARCHAR (1),
    PRIMARY KEY(ID)
);

CREATE TABLE USERS_ROLES
(
    USER_ID VARCHAR (36),
    ROLE_ID VARCHAR (36),
    PRIMARY KEY(USER_ID, ROLE_ID)
);

CREATE TABLE oauth_client_details
(
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256) NOT NULL,
  scope VARCHAR(256) NOT NULL,
  authorized_grant_types VARCHAR(256) NOT NULL,
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER NOT NULL,
  refresh_token_validity INTEGER NOT NULL,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

CREATE TABLE oauth_client_token
(
  authentication_id VARCHAR(256) PRIMARY KEY,
  token_id VARCHAR(256),
  token BLOB,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

CREATE TABLE oauth_access_token
(
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

CREATE TABLE oauth_refresh_token
(
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

CREATE TABLE oauth_code
(
  code VARCHAR(256),
  authentication BLOB
);
--rollback;
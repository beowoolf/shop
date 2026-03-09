--liquibase formatted sql
--changeset pwilczek:19
create unique index ix_auth_username on authorities (username,authority);

--liquibase formatted sql
--changeset pwilczek:23
alter table users add hash varchar(120);

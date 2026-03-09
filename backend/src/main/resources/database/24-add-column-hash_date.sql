--liquibase formatted sql
--changeset pwilczek:24
alter table users add hash_date datetime;

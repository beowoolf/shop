--liquibase formatted sql
--changeset pwilczek:9
alter table review add moderated boolean  default false;

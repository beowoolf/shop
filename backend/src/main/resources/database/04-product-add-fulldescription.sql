--liquibase formatted sql
--changeset pwilczek:4
alter table product add full_description text default null after description;

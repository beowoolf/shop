--liquibase formatted sql
--changeset pwilczek:2
alter table product add image varchar(128) after currency;
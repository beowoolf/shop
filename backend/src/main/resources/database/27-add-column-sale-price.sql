--liquibase formatted sql
--changeset pwilczek:27
alter table product add sale_price decimal(9, 2);

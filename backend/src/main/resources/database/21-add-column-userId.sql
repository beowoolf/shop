--liquibase formatted sql
--changeset pwilczek:21
alter table `order` add user_id bigint;

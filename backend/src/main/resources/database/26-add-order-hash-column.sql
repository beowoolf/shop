--liquibase formatted sql
--changeset pwilczek:26
alter table `order` add order_hash varchar(12);

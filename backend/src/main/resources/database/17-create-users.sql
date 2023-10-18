--liquibase formatted sql
--changeset pwilczek:17
create table users(
    id bigint not null auto_increment PRIMARY KEY,
	username varchar(50) not null unique,
	password varchar(500) not null,
	enabled boolean not null
);

--liquibase formatted sql
--changeset pwilczek:22
alter table `order` add constraint fk_order_user_id foreign key (user_id) references users(id);

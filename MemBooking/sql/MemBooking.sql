drop database if exists MemBooking;
create database MemBooking/*!40100 COLLATE 'utf8_general_ci' */;
use MemBooking;

create table mb_guests (gender varchar(255) null ,description text null ,guest_name varchar(255) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table mb_rooms (room_no varchar(255) null ,description text null ,room_name varchar(255) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table mb_bookings (room_id bigint null ,occu_date varchar(255) null ,description text null ,booking_name varchar(255) null ,active bool null ,id BigInt not null auto_increment,primary key (id));


insert into mb_guests ( gender,description,guest_name,active,id) values ('Male','Jerry','Jerry',true,1);
insert into mb_guests ( gender,description,guest_name,active,id) values ('Female','Mala','Mala',true,2);
insert into mb_guests ( gender,description,guest_name,active,id) values ('Female','Linda','Linda',true,3);
insert into mb_guests ( gender,description,guest_name,active,id) values ('Male','Peter','Peter',true,4);

insert into mb_rooms ( room_no,description,room_name,active,id) values ('1','Roma','Roma',true,1);
insert into mb_rooms ( room_no,description,room_name,active,id) values ('2','New York','New York',true,2);
insert into mb_rooms ( room_no,description,room_name,active,id) values ('3','Washington','Washington',true,3);
insert into mb_rooms ( room_no,description,room_name,active,id) values ('4','London','London',true,4);
insert into mb_rooms ( room_no,description,room_name,active,id) values ('5','Paris','Paris',true,5);

insert into mb_bookings ( room_id,occu_date,description,booking_name,active,id) values ('1','2022-04-20','','Booking1',true,1);
insert into mb_bookings ( room_id,occu_date,description,booking_name,active,id) values ('2','2022-04-19','','Booking2',true,2);

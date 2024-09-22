create database hotelmanagement;
show databases;
use hotelmanagement;
create table login(username varchar(25) , password varchar(25)); 
insert into login values ('naveen' , '1234');
insert into login values('sagar' , '12345') ;
select * from login ;

create  table employee(name varchar(25), age varchar(25), gender varchar(25) , job varchar(35) , salary varchar(15) , phone varchar(15),email varchar(15) , aadhar varchar(20) not null );
describe employee;



create table room(room_number varchar(25) , availability varchar(20) ,cleaning_status varchar(20) ,price varchar(20) ,bed_type varchar(20));
select * from room ;
select * from login ;
CREATE TABLE customer (
    id VARCHAR(50) NOT NULL,
    number VARCHAR(50) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    country VARCHAR(50) NOT NULL,
    room VARCHAR(10) NOT NULL,
    status VARCHAR(50) NOT NULL,
    deposit VARCHAR(50) NOT NULL
);







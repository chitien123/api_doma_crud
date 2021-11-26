create database STUDY
use STUDY

create table STUDENTS
(
	Id int,
	FullName varchar(30),
	Age int,
	Gender varchar(6),
	Email varchar(50),
	Phone varchar(12),
	TeacherId int,
	Constraint PK_STUDENTS primary key(Id)
)
go

create sequence refresh_token_ceq 
	START WITH 1
    Increment by 1;

create table USERS
(
	ID int ,
	UserName nvarchar(10) null,
	Password nvarchar(256) null,
	primary key(id)
)
insert into USERS
values(1,'chitien','$2a$10$oZsgGJLBb6VsHot0bNopYOfRrMEzmA8W6yqkeFJrEmHGTCushm81O')
insert into USERS
values(7,'nguynhoang','$2a$10$oZsgGJLBb6VsHot0bNopYOfRrMEzmA8W6yqkeFJrEmHGTCushm81O')


create table TEACHER
(
	Id int,
	FullName varchar(30),
	Age int,
	Gender varchar(6),
	ClassName varchar(30),
	UniversityId int,
	Constraint PK_TEACHER primary key(Id)
)

create table University
(
	Id int,
	FullName varchar(30),
	Locations varchar(30),
	Constraint PK_University primary key(Id)
)

create table refresh_token
(
	Id int,
	Token nvarchar(100),
	expiry_DT date,
	UserId int,
	primary key(id)
)
insert into refresh_token
values(199,'34fe661c-f4d2-4d51-b6f7-d229d2b79aee','2021-11-23',7)

drop table refresh_token
drop table USERS

select * from STUDENTS
select * from TEACHER
select * from University
select * from USERS
select * from refresh_token

delete STUDENTS
delete TEACHER
delete University
delete users



drop sequence refresh_token_ceq
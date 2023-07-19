drop database if exists pawsomepets;
create database pawsomepets;
use pawsomepets;

create table pet(
petID int auto_increment primary key,
petName varchar(20) not null,
petAvailability varchar(15) not null,
petDesc varchar(250),
petGender varchar(10) not null,
petSize varchar(6) not null,
petAge int not null,
petType varchar(25) not null,
petBreed varchar(50) not null,
petImage varchar(250));

create table user(
userID int primary key auto_increment ,
userName varchar(20) not null,
password varchar(30) not null,
userType varchar(15) not null,
firstName varchar(20) not null,
lastName varchar(20) not null,
email varchar(40) not null,
phoneNumber varchar(15) not null);

create table application(
applicationID int primary key auto_increment,
petID int not null,
userID int not null,
applicationDate date not null,
pickupDate date not null,
constraint foreign key(petID) references pet(petID),
constraint foreign key(userID) references user(userID));

create table wishlist(
wishlistID int primary key auto_increment,
userID int not null,
petID int not null,
constraint foreign key(userID) references user(userID),
constraint foreign key(petID) references pet(petID));

insert into user
values(1,"admin","adminPass","ADMIN","Steve","Irwin","SteveIrwin@email.com","32153432"),
(2,"user","userPass","USER","Margot","Robbie","MargotRobbie@email.com","783526423");

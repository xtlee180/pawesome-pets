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

drop database if exists pawsomeUsers;
create database pawsomeUsers;
use pawsomeUsers;

create table user(
userID int primary key auto_increment ,
userName varchar(20) not null unique,
userPassword varchar(30) not null,
userType varchar(15) not null,
firstName varchar(20) not null,
lastName varchar(20) not null,
email varchar(40) not null,
phoneNumber varchar(15) not null);

insert into user
values(1,"admin","adminPass","ADMIN","Steve","Irwin","SteveIrwin@email.com","32153432"),
(2,"user","userPass","USER","Margot","Robbie","MargotRobbie@email.com","783526423");

drop database if exists pawsomeApplication;
create database pawsomeApplication;
use pawsomeApplication;

create table application(
applicationID int primary key auto_increment,
petID int not null,
userID int not null,
applicationDate date not null,
pickupDate date not null);

drop database if exists pawsomeWish;
create database pawsomeWish;
use pawsomeWish;

CREATE TABLE wishlists (
  wishListID int PRIMARY KEY AUTO_INCREMENT,
  listName VARCHAR(255) NOT NULL,
  userID int NOT NULL);

CREATE TABLE wishlist_pets (
  wishListID int NOT NULL,
  petID BIGINT NOT NULL,
  PRIMARY KEY (wishListID, petID),
  FOREIGN KEY (wishListID) REFERENCES wishlists (wishListID));
  
  




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

INSERT INTO pet(petID, petName, petAvailability, petDesc, petGender, petSize, petAge, petType, petBreed)
VALUES (101, 'Buddy', 'AVAILABLE', 'A friendly and loyal companion, loves playing fetch.', 'MALE', 'MEDIUM', 3, 'Dog','Golden Retriever'),
(102, 'Luna', 'AVAILABLE', 'An elegant and independent feline, enjoys lounging in sunspots.', 'FEMALE', 'SMALL', 2,'Cat', 'Siamese'),
(103, 'Kiwi', 'AVAILABLE', 'A colorful and talkative parrot, loves mimicking sounds.', 'MALE', 'SMALL', 5, 'Bird', 'African Grey Parrot'),
(104, 'Snowball', 'ADOPTED', 'A cute and cuddly rabbit, enjoys hopping around.', 'FEMALE', 'SMALL', 1 ,'Rabbit', 'Dwarf Rabbit'),
(105,'Bubbles', 'AVAILABLE', 'A colorful and peaceful fish, adds serenity to any aquarium.', 'UNKNOWN', 'SMALL', 1, 'Fish','Betta Fish'),
(106,'Spike', 'AVAILABLE', 'A slithery and low-maintenance snake, a unique pet.', 'MALE', 'MEDIUM', 4,'Reptile', 'Ball Python'),
(107,'Nibbles', 'AVAILABLE', 'An adorable and active hamster, enjoys running on wheels.', 'FEMALE', 'SMALL', 1,'Small Buddies', 'Syrian Hamster'),
(108,'Sheldon', 'AVAILABLE', 'A slow and wise turtle, needs a comfortable basking area.', 'MALE', 'SMALL', 3,'Reptile', 'Red-Eared Slider'),
(109,'Cocoa', 'AVAILABLE', 'A gentle and social guinea pig, loves fresh vegetables.', 'FEMALE', 'SMALL', 2,'Guinea Pig', 'Abyssinian Guinea Pig'),
(110,'Bandit', 'AVAILABLE', 'An energetic and mischievous ferret, needs regular playtime.', 'MALE', 'SMALL', 1,'Ferret', 'Sable Ferret'),
(111, 'Maisie', 'ADOPTED', 'A friendly and shy hamster who loves burrowing and exploring.', 'FEMALE', 'SMALL', 1, 'Small Buddies', 'Dwarf Hamster');


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


create table wishlist (
  wishlistID int primary key auto_increment,
  wishlistName varchar(255) not null,
  userID int not null);


create table wishlist_pets (
  wishlistID int not null,
  petID int not null,
  primary key (wishlistID, petID),
 foreign key (wishlistID) references wishlist (wishlistID));
  
  




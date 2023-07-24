package com.pets.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/* table in sql
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
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pet {

    @Id
    private int petID;
    private String petName;

    @Enumerated(EnumType.STRING)
    @Column(name="petAvailability")
    private AvailEnum availability;

    @Column(name="petDesc")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="petGender")
    private GenderEnum gender;

    @Enumerated(EnumType.STRING)
    @Column(name="petSize")
    private SizeEnum size;

    @Column(name="petAge")
    private int age;

    @Column(name="petType")
    private String type;

    @Column(name="petBreed")
    private String breed;

    //contains url of the animal
    @Column(name="petImage")
    private String image;
}

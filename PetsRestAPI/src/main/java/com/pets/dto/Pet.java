package com.pets.dto;


import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class Pet {
    private int petID;
    private String petName;

    @Enumerated(EnumType.STRING)
    @Column(name="petAvailability")
    private AvailEnum availablity;
    private String petDesc;
    private GenderEnum petGender;
    private SizeEnum petSize;
    private int petAge;
    private String petType;
    private String petBreed;

    //contains url of the animal
    private String petImage;
}

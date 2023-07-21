package com.pawsomepets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistID;
    @Column(nullable = false)
    private String wishlistName;
    @Column(nullable = false)
    private int userID;

    @ElementCollection
    @CollectionTable(name = "wishlist_pets", joinColumns = @JoinColumn(name = "wishlistID"))
    @Column(name = "petID")
    private List<Integer> petIDs = new ArrayList<>();

}
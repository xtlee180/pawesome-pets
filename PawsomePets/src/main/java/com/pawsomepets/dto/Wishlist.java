package com.pawsomepets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wishlists")
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

    // Convenience method to add a petId to the wishlist
    public void addPetId(int petID) {
        this.petIDs.add(petID);
    }

    // Convenience method to remove a petId from the wishlist
    public void removePetId(int petID) {
        this.petIDs.remove(petID);
    }

}
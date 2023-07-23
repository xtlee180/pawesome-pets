package com.pawsomepets.service;

import com.pawsomepets.dto.Wishlist;

import java.util.List;

public interface WishlistService {

    Wishlist createWishlist(Wishlist wishlist);
    Wishlist getWishlistByID(int wishlistID);
    List<Wishlist> getAllWishlists();
    List<Wishlist> getWishlistsByUserID(int userID);
    void updateWishlist(Wishlist wishlist);
    void deleteWishlist(int wishlistID);
    void addPetToWishlist(int wishlistID, int petID);
    void removePetFromWishlist(int wishlistID, int petID);
}

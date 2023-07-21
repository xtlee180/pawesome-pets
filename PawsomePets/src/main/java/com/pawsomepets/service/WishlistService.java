package com.pawsomepets.service;

import com.pawsomepets.dto.Wishlist;

import java.util.List;

public interface WishlistService {

    List<Wishlist> getAllWishlists();
    Wishlist getWishlistByID(int wishlistID);
    List<Wishlist> getWishlistsByUserID(int userID);
    boolean addWishlist(Wishlist wishlist);
    Wishlist addPetToWishlist(int wishlistID, int petID);
    boolean editWishlist(Wishlist wishlist);
    boolean deleteWishlistByID(int wishlistID);
}

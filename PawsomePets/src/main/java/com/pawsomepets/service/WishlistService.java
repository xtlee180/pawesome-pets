package com.pawsomepets.service;

import com.pawsomepets.service.dto.Wishlist;

import java.util.List;

public interface WishlistService {

    List<Wishlist> getAllWishlists();
    Wishlist getWishlistByID(int wishlistID);
    boolean addWishlist(Wishlist wishlist);
    boolean deleteWishlistByID(int wishlistID);
    boolean editWishlist(Wishlist wishlist);

}

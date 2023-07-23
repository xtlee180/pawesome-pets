package com.pawsomepets.service;

import com.pawsomepets.dao.WishlistDao;
import com.pawsomepets.dto.Wishlist;
import com.pawsomepets.dto.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService{


    @Autowired
    WishlistDao wishlistDao;

    // create a wishlist
    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistDao.save(wishlist);
    }

    // return a list of wishlists by ID
    public List<Wishlist> getAllWishlists() {
        return wishlistDao.findAll();
    }

    // return a wishlist by ID
    public Wishlist getWishlistByID(int wishlistID) {
        return wishlistDao.findById(wishlistID).orElse(null);
    }

    // returns a list of wishlists by userID
    public List<Wishlist> getWishlistsByUserID(int userID) {
        return wishlistDao.findByUserID(userID);
    }

    // updates the edited wishlist
    public void updateWishlist(Wishlist wishlist) {
        wishlistDao.save(wishlist);
    }

    // delete a wishlist by ID
    public void deleteWishlist(int wishlistID) {
        wishlistDao.deleteById(wishlistID);
    }

    // add a pet to the wishlist
    public void addPetToWishlist(int wishlistID, int petID) {
        Wishlist wishlist = getWishlistByID(wishlistID);
        if (wishlist != null) {
            wishlist.addPetId(petID);
            wishlistDao.save(wishlist);
        }
    }

    // remove a pet from the wishlist
    public void removePetFromWishlist(int wishlistID, int petID) {
        Wishlist wishlist = wishlistDao.findById(wishlistID).orElse(null);
        if (wishlist != null) {
            wishlist.removePetId(petID-1);
            wishlistDao.save(wishlist);
        }
    }

}

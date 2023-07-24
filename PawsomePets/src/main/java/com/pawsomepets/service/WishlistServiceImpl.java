package com.pawsomepets.service;

import com.pawsomepets.dao.WishlistDao;
import com.pawsomepets.dto.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService{


    @Autowired
    WishlistDao wishlistDao;

    // create a wishlist
    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistDao.save(wishlist);
    }

    // return a list of wishlists by ID
    @Override
    public List<Wishlist> getAllWishlists() {
        return wishlistDao.findAll();
    }

    // return a wishlist by ID
    @Override
    public Wishlist getWishlistByID(int wishlistID) {
        return wishlistDao.findById(wishlistID).orElse(null);
    }

    // returns a list of wishlists by userID
    @Override
    public List<Wishlist> getWishlistsByUserID(int userID) {
        return wishlistDao.findByUserID(userID);
    }

    // updates the edited wishlist
    @Override
    public void updateWishlist(Wishlist wishlist) {
        wishlistDao.save(wishlist);
    }

    // delete a wishlist by ID
    @Override
    public void deleteWishlist(int wishlistID) {
        wishlistDao.deleteById(wishlistID);
    }

//    @Override
//    public List<Integer> getPetIdsForWishlist(int wishlistId) {
//        System.out.println("Fetching pet IDs for wishlistId: " + wishlistId);
//        // Implement the logic to fetch pet IDs associated with the given wishlistId from the wishlist_pets table
//        // Return a List<Integer> containing the pet IDs
//        // Example:
//        List<Integer> petIds = wishlistDao.findById(wishlistId)
//                .map(Wishlist::getPetIDs)
//                .orElse(Collections.emptyList());
//        System.out.println("Fetched pet IDs: " + petIds);
//        return petIds;
//    }

    @Override
    public List<Integer> getPetIdsForWishlist(int wishlistID) {
        Wishlist wishlist = wishlistDao.findById(wishlistID).orElse(null);
        if (wishlist != null) {
            return wishlist.getPetIDs();
        }
        return Collections.emptyList();
    }


    // add a pet to the wishlist
    @Override
    public void addPetToWishlist(int wishlistID, int petID) {
        Wishlist wishlist = getWishlistByID(wishlistID);
        if (wishlist != null) {
            wishlist.addPetId(petID);
            wishlistDao.save(wishlist);
        }
    }

    // remove a pet from the wishlist
    @Override
    public void removePetFromWishlist(int wishlistID, int petID) {
        Wishlist wishlist = wishlistDao.findById(wishlistID).orElse(null);
        if (wishlist != null) {
            wishlist.removePetId(petID-1);
            wishlistDao.save(wishlist);
        }
    }

}

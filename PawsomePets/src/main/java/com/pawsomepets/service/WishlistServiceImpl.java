package com.pawsomepets.service;

import com.pawsomepets.dao.WishlistDao;
import com.pawsomepets.dto.Wishlist;
import com.pawsomepets.dto.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService{

    @Autowired
    private WishlistDao wishlistDao;
    @Autowired
    private RestTemplate restTemplate;

    public List<Wishlist> getAllWishlists() {
        return wishlistDao.findAll();
    }

    public Wishlist getWishlistByID(int wishlistID) {
        return wishlistDao.findById(wishlistID)
                .orElseThrow(() -> new NotFoundException("Wishlist not found"));
    }

    public List<Wishlist> getWishlistsByUserID(int userID) {
        return wishlistDao.findByUserID(userID);
    }

    public boolean addWishlist(Wishlist wishlist) {
        try {
            if (wishlistDao.insertWishlist(wishlist.getWishlistName(), wishlist.getUserID()) > 0) {
                return true;
            }
        } catch(Exception ex) {
            return false;
        }
        return false;
//        return wishlistDao.save(wishlist);
    }

    public Wishlist addPetToWishlist(int wishlistID, int petID) {
        ResponseEntity<Pet> response = restTemplate.getForEntity("http://pet-service/api/pets/%7Bid%7D", Pet.class, petID);
        if (response.getStatusCode().is2xxSuccessful()) {
            Pet pet = response.getBody();
            if (pet != null) {
                Wishlist wishlist = getWishlistByID(wishlistID);
                wishlist.getPetIDs().add(petID);
                return wishlistDao.save(wishlist);
            }
        }
        throw new NotFoundException("Pet not found");
    }

    public boolean editWishlist(Wishlist wishlist) {
        try {
            wishlistDao.updateWishlist(wishlist.getWishlistID(), wishlist.getWishlistName(), wishlist.getUserID());
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public boolean deleteWishlistByID(int wishlistID) {
        if (wishlistDao.existsById(wishlistID)) {
            wishlistDao.deleteById(wishlistID);
            return true;
        } else {
            throw new NotFoundException("Wishlist not found");
        }
    }

}

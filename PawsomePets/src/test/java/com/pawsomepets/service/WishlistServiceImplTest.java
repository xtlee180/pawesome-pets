package com.pawsomepets.service;

import com.pawsomepets.demo.TestApplicationConfiguration;
import com.pawsomepets.dto.Wishlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.pawsomepets.service.WishlistService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class WishlistServiceImplTest {

    @Autowired
    WishlistService wishlistService;

    @BeforeEach
    void setUp() {
        List<Wishlist> wishlists = wishlistService.getAllWishlists();
        for(Wishlist wishlist: wishlists) {
            wishlistService.deleteWishlistByID((wishlist.getWishlistID()));
        }
    }

    @Test
    void testAddGetWishlist() {
        // setting dummy data
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistName("Test Wishlist Name");
        wishlist.setUserID(1);

//        // check if power has been added
//        assertEquals(wishlistService.addWishlist(wishlist), 0);

        // check if the power has been found
        Wishlist fromService = wishlistService.getWishlistByID(wishlist.getWishlistID());
        assertEquals(wishlist, fromService);
    }

    @Test
    void testGetAllWishlists() {
        // setting dummy data
        Wishlist wishlist1 = new Wishlist();
        wishlist1.setWishlistName("Test Wishlist Name1");
        wishlist1.setUserID(1);
        wishlistService.addWishlist(wishlist1);

        Wishlist wishlist2 = new Wishlist();
        wishlist2.setWishlistName("Test Wishlist Name2");
        wishlist2.setUserID(2);
        wishlistService.addWishlist(wishlist2);

        // adding all wishlists to a list
        List<Wishlist> wishlists = wishlistService.getAllWishlists();

        // check if there are 2 objects in the list
        assertEquals(2, wishlists.size());

        // that the list contains both wishlist
        assertTrue(wishlists.contains(wishlist1));
        assertTrue(wishlists.contains(wishlist1));
    }



    @Test
    void getWishlistsByUserID() {
        // setting dummy data
        Wishlist wishlist1 = new Wishlist();
        wishlist1.setWishlistName("Test Wishlist Name1");
        wishlist1.setUserID(1);
        wishlistService.addWishlist(wishlist1);

        Wishlist wishlist2 = new Wishlist();
        wishlist2.setWishlistName("Test Wishlist Name2");
        wishlist2.setUserID(1);
        wishlistService.addWishlist(wishlist2);

        // adding all wishlists to a list
        List<Wishlist> wishlists = wishlistService.getWishlistsByUserID(1);

        // check if there are 2 objects in the list
        assertEquals(2, wishlists.size());

        // that the list contains both wishlist
        assertTrue(wishlists.contains(wishlist1));
        assertTrue(wishlists.contains(wishlist1));
    }

    @Test
    void addPetToWishlist() {

    }

    @Test
    void editWishlist() {
        // setting dummy data
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistName("Test Wishlist Name");
        wishlist.setUserID(1);
        wishlistService.addWishlist(wishlist);

        // check if the power just added exist
        Wishlist fromService = wishlistService.getWishlistByID(wishlist.getWishlistID());
        assertEquals(wishlist, fromService);

        // edit the power
        wishlist.setWishlistName("New Wishlist Name");
        wishlist.setUserID(2);
        wishlistService.editWishlist(wishlist);

        // check that it's not the same
        assertNotEquals(wishlist, fromService);

        // check that the power has been edited
        fromService = wishlistService.getWishlistByID(wishlist.getWishlistID());
        assertEquals(wishlist, fromService);
    }

    @Test
    void deleteWishlistByID() {
        // setting dummy data
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistName("Test Wishlist Name");
        wishlist.setUserID(1);
        wishlistService.addWishlist(wishlist);

        // check if the power just added existWishlists powerFromDao = superPowersDao.getPowerByID(superPowers.getPowerID());
        Wishlist wishlistFromService = wishlistService.getWishlistByID(wishlist.getWishlistID());
        assertEquals(wishlist, wishlistFromService);

        // delete the power
        wishlistService.deleteWishlistByID(wishlist.getWishlistID());

        // check if the list is empty meaning the power has been deleted
        Wishlist newFromService = wishlistService.getWishlistByID(wishlist.getWishlistID());
        assertNull(newFromService );
    }
}
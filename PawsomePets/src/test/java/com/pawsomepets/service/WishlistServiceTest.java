package com.pawsomepets.service;

import com.pawsomepets.dao.WishlistDao;
import com.pawsomepets.dto.Wishlist;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WishlistServiceTest {

    @Mock
    private WishlistDao wishlistDao;

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    // Test data
    private Wishlist wishlist1;
    private Wishlist wishlist2;
    private List<Wishlist> wishlistList;

    @BeforeEach
    public void setUp() {
        // set dummy data
        wishlist1 = new Wishlist();
        wishlist1.setWishlistID(1);
        wishlist1.setUserID(101);
        wishlist1.setWishlistName("My First Wishlist");
        wishlist1.setPetIDs(new ArrayList<>());

        wishlist2 = new Wishlist();
        wishlist2.setWishlistID(2);
        wishlist2.setUserID(101);
        wishlist2.setWishlistName("My Second Wishlist");
        wishlist2.setPetIDs(new ArrayList<>());

        wishlistList = List.of(wishlist1, wishlist2);
    }

    @Test
    public void testCreateWishlist() {
        // Set up
        when(wishlistDao.save(any())).thenReturn(wishlist1);

        // Exercise
        Wishlist createdWishlist = wishlistService.createWishlist(wishlist1);

        // Verify
        assertNotNull(createdWishlist);
        assertEquals(wishlist1.getWishlistName(), createdWishlist.getWishlistName());
    }

    @Test
    public void testGetWishlistByID() {
        // Set up
        when(wishlistDao.findById(1)).thenReturn(Optional.of(wishlist1));

        // Exercise
        Wishlist foundWishlist = wishlistService.getWishlistByID(1);

        // Verify
        assertNotNull(foundWishlist);
        assertEquals(wishlist1.getWishlistName(), foundWishlist.getWishlistName());
    }

    @Test
    public void testGetWishlistByID_NotFound() {
        // Set up
        when(wishlistDao.findById(99)).thenReturn(Optional.empty());

        // Exercise
        Wishlist foundWishlist = wishlistService.getWishlistByID(99);

        // Verify
        assertNull(foundWishlist);
    }


    @Test
    public void testGetAllWishlists() {
        // Set up
        when(wishlistDao.findAll()).thenReturn(wishlistList);

        // Exercise
        List<Wishlist> wishlists = wishlistService.getAllWishlists();

        // Verify
        assertNotNull(wishlists);
        assertEquals(wishlistList.size(), wishlists.size());
    }

    @Test
    public void testGetWishlistsByUserID() {
        // Set up
        when(wishlistDao.findByUserID(101)).thenReturn(wishlistList);

        // Exercise
        List<Wishlist> userWishlists = wishlistService.getWishlistsByUserID(101);

        // Verify
        assertNotNull(userWishlists);
        assertEquals(wishlistList.size(), userWishlists.size());
    }

    @Test
    public void testUpdateWishlist() {
        // Set up
        when(wishlistDao.save(any())).thenReturn(wishlist1);

        // Exercise
        wishlist1.setWishlistName("Updated Wishlist Name");
        wishlistService.updateWishlist(wishlist1);

        // Verify
        verify(wishlistDao, times(1)).save(wishlist1);
    }

    @Test
    public void testDeleteWishlist() {
        // Exercise
        wishlistService.deleteWishlist(1);

        // Verify
        verify(wishlistDao, times(1)).deleteById(1);
    }

    @Test
    public void testAddPetToWishlist() {
        // Set up
        when(wishlistDao.findById(1)).thenReturn(Optional.of(wishlist1));
        when(wishlistDao.save(any())).thenReturn(wishlist1);

        // Exercise
        wishlistService.addPetToWishlist(1, 1001);

        // Verify
        assertTrue(wishlist1.getPetIDs().contains(1001));
        verify(wishlistDao, times(1)).save(wishlist1);
    }

    @Test
    public void testRemovePetFromWishlist() {
        // Set up
        wishlist1.addPetId(1);
        when(wishlistDao.findById(1)).thenReturn(Optional.of(wishlist1));
        when(wishlistDao.save(any())).thenReturn(wishlist1);

        // Exercise
        wishlistService.removePetFromWishlist(1, 1);

        // Verify
        assertFalse(wishlist1.getPetIDs().contains(1));
        verify(wishlistDao, times(1)).save(wishlist1);
    }

}
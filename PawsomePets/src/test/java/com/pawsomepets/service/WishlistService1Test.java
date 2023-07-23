//package com.pawsomepets.service;
//
//import com.pawsomepets.dao.WishlistDao;
//import com.pawsomepets.demo.TestApplicationConfiguration;
//import com.pawsomepets.dto.Wishlist;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = TestApplicationConfiguration.class)
//public class WishlistService1Test {
//
//    @Autowired
//    private WishlistService1 wishlistService1;
//
//    @Mock
//    WishlistDao wishlistDao;
//
//    private Wishlist sampleWishlist;
//
//    @BeforeEach
//    public void setup() {
//        // Initialize sample Wishlist and Pet objects for testing
//        sampleWishlist = new Wishlist();
//        sampleWishlist.setWishlistID(1);
//        sampleWishlist.setWishlistName("Test Wishlist");
//        sampleWishlist.setUserID(101);
//        List<Integer> petIDs = new ArrayList<>();
//        petIDs.add(1);
//        sampleWishlist.setPetIDs(petIDs);
//
////        samplePet = new Pet();
////        samplePet.setPetID(1001);
////        samplePet.setName("Sample Pet");
//    }
////    @Test
////    public void testGetAllWishlists() {
////        // Prepare test data
////        List<Wishlist> wishlists = new ArrayList<>();
////        wishlists.add(sampleWishlist);
////
////        // Mock the behavior of the DAO method
//////        when(wishlistDao.findAll()).thenReturn(wishlists);
////
////        // Perform the test
////        List<Wishlist> result = wishlistService.getAllWishlists();
////        assertEquals(1, result.size());
////        assertEquals(sampleWishlist.getWishlistID(), result.get(0).getWishlistID());
////    }
////
//
//    @Test
//    public void testGetAllWishlists() {
//        List<Wishlist> wishlists = Arrays.asList(
//                new Wishlist(1, "My Wishlist 1", 101, Arrays.asList(1, 2, 3)),
//                new Wishlist(2, "My Wishlist 2", 102, Arrays.asList(4, 5))
////                new Wishlist(1, "My Wishlist 1", 101),
////                new Wishlist(2, "My Wishlist 2", 102)
//        );
//
//        when(wishlistDao.findAll()).thenReturn(wishlists);
//
//        List<Wishlist> result = wishlistService1.getAllWishlists();
//        assertEquals(2, result.size());
//        // Add more assertions based on the actual data returned
//    }
//}

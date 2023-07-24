package com.pawsomepets.controller;

import com.pawsomepets.dto.Wishlist;
import com.pawsomepets.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/")
    public String displaypage(Model model) {
        return "home";
    }

    @GetMapping
    public String showUserWishlist(@RequestParam(required = false) Integer userID, Model model) {
        // Get the logged-in user's details from Spring Security Authentication
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();

        // Now, you can use the username to retrieve the actual user from your database
        // For example, if you have a UserService to manage users, you can do something like:
        // User user = userService.getUserByUsername(username);

        // For this example, let's assume you have the user's ID as "1" for demonstration purposes.
        if (userID == null) {
            // If userID is not provided, set it to the ID of the logged-in user
            // Replace 1 with the actual user ID retrieved from your database.
            userID = 1;
        }

        // Retrieve the wishlists specific to the user with the given userID
        List<Wishlist> userWishlists = wishlistService.getWishlistsByUserID(userID);

        model.addAttribute("wishlists", userWishlists);
        return "Wishlist/Wishlist"; // Return the Wishlist.html page
    }

    @PostMapping
    public String createWishlist(@ModelAttribute Wishlist wishlist) {
        // Get the logged-in user's details from Spring Security Authentication
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();

        // Now, you can use the username to retrieve the actual user from your database
        // For example, if you have a UserService to manage users, you can do something like:
        // User user = userService.getUserByUsername(username);

        // For this example, let's assume you have the user's ID as "1" for demonstration purposes.
        int userID = 1;
        wishlist.setUserID(userID);
        wishlistService.createWishlist(wishlist);
        return "redirect:/wishlists"; // Redirect back to the wishlist page
    }

    @GetMapping("/{wishlistID}")
    public String editWishlist(@PathVariable int wishlistID, Model model) {
        Wishlist wishlist = wishlistService.getWishlistByID(wishlistID);
        model.addAttribute("wishlist", wishlist);
        return "edit_wishlist"; // Create an edit_wishlist.html page for editing
    }

    @PostMapping("/{wishlistID}")
    public String updateWishlist(@PathVariable int wishlistID, @ModelAttribute Wishlist updatedWishlist) {
        Wishlist wishlist = wishlistService.getWishlistByID(wishlistID);
        wishlist.setWishlistName(updatedWishlist.getWishlistName());
        wishlistService.updateWishlist(wishlist);
        return "redirect:/wishlists"; // Redirect back to the wishlist page
    }

    @PostMapping("/{wishlistID}/delete")
    public String deleteWishlist(@PathVariable int wishlistID) {
        wishlistService.deleteWishlist(wishlistID);
        return "redirect:/wishlists"; // Redirect back to the wishlist page
    }


    @GetMapping("/{wishlistId}/pets")
    @ResponseBody
    public ResponseEntity<List<Integer>> getPetIdsForWishlist(@PathVariable int wishlistId) {
        List<Integer> petIds = wishlistService.getPetIdsForWishlist(wishlistId);
        return ResponseEntity.ok(petIds);
    }
}
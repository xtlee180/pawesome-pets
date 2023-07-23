package com.pawsomepets.controller;

import com.pawsomepets.dto.Wishlist;
import com.pawsomepets.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
//@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    Set<ConstraintViolation<Wishlist>> violations = new HashSet<>();

    // display all wishlists
    @GetMapping( "/wishlists")
    public String displayWishlists(Model model) {
        List<Wishlist> wishlists = wishlistService.getAllWishlists();
        model.addAttribute("wishList", wishlists);
        model.addAttribute("errors", violations);
        return "wishlists";
    }

    // display a wishlist by ID
    @GetMapping("/displayWishlistByID")
    public String displayWishlistByID(Integer wishlistID, Model model) {
        Wishlist wishlist = wishlistService.getWishlistByID(wishlistID);
        model.addAttribute("wishList", wishlist);
        model.addAttribute("errors", violations);
        return "wishlists";
    }

    // display all wishlists by userID
    @GetMapping("/displayWishlistByUserID")
    public String displayWishlistByUserID (Integer userID, Model model) {
        List<Wishlist> wishlists = wishlistService.getWishlistsByUserID(userID);
        model.addAttribute("wishList", wishlists);
        model.addAttribute("errors", violations);
        return "wishlists";
    }

    // create a wishlist
    @PostMapping("/createWishlist")
    public String addWishlist(@Valid Wishlist wishlist, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "wishlists";
        }
        wishlistService.createWishlist(wishlist);
        return "redirect:/wishlists";
    }

    // update a wishlist
    @GetMapping("/updateWishlist")
    public String updateWishlist(Integer wishlistID, Model model) {
        Wishlist wishlist = wishlistService.getWishlistByID(wishlistID);
        model.addAttribute("wishlist", wishlist);
        return "updateWishList";
    }

    // perform the update
    @PostMapping("/updateWishlist")
    public String performupdateSuperPower(@Valid Wishlist wishlist, BindingResult result) {
        if (result.hasErrors()) {
            return "/updateWishlist";
        }
        wishlistService.updateWishlist(wishlist);
        return "redirect:/wishlists";
    }

    // delete a wishlist
    @GetMapping("/deleteWishlistByID")
    public String deleteWishlistByID(Integer wishlistID, Model model) {
        Wishlist wishlist = wishlistService.getWishlistByID(wishlistID);
        model.addAttribute("wishlist", wishlist);
        return "deleteWishlistByID";
    }

    // peform the delete
    @PostMapping("/deleteWishlistByID")
    public String performDeleteWishlistByID(Integer wishlistID) {
        wishlistService.deleteWishlist(wishlistID);
        return "redirect:/wishlists";
    }

    // add a pet to a wishlist
    @PostMapping("/addPetToWishlist")
    public String addPetToWishlist(Integer wishlistID, Integer petID) {
        wishlistService.addPetToWishlist(wishlistID, petID);
        return "redirect:/wishlists";
    }

    // remove a pet from a wishlist
    @PostMapping("/removePetFromWishlist")
    public String removePetFromWishlist(Integer wishlistID, Integer petID) {
        wishlistService.removePetFromWishlist(wishlistID, petID);
        return "redirect:/wishlists";
    }
}

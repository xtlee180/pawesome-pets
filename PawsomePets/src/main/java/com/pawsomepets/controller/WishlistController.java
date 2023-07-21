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

    @GetMapping( "/wishlists")
    public String displayWishlists(Model model) {
        List<Wishlist> wishlists = wishlistService.getAllWishlists();
        model.addAttribute("wishList", wishlists);
        model.addAttribute("errors", violations);
        return "wishlists";
    }

    @GetMapping("/displayWishlistByID")
    public String displayWishlistByID(Integer wishlistID, Model model) {
        Wishlist wishlist = wishlistService.getWishlistByID(wishlistID);
        model.addAttribute("wishList", wishlist);
        model.addAttribute("errors", violations);
        return "wishlists";
    }

    @GetMapping("/displayWishlistByUserID")
    public String displayWishlistByUserID (Integer userID, Model model) {
        List<Wishlist> wishlists = wishlistService.getWishlistsByUserID(userID);
        model.addAttribute("wishList", wishlists);
        model.addAttribute("errors", violations);
        return "wishlists";
    }

    @PostMapping("/addWishlist")
    public String addWishlist(@Valid String wishlistName, Integer userID) {
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistName(wishlistName);
        wishlist.setUserID(userID);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(wishlist);

        if (violations.isEmpty()) {
            wishlistService.addWishlist(wishlist);
        }
        return "redirect:/wishlists";
    }

    @PostMapping("/addPetToWishlist")
    public String addPetToWishlist(Integer wishlistID, Integer petID) {
        wishlistService.addPetToWishlist(wishlistID, petID);
        return "redirect:/wishlists";
    }

    @GetMapping("/editWishlist")
    public String editWishlist(Integer wishlistID, Model model) {
        Wishlist wishlist = wishlistService.getWishlistByID(wishlistID);
        model.addAttribute("wishlist", wishlist);
        return "editWishList";
    }

    @PostMapping("editSuperPower")
    public String performEditSuperPower(@Valid Wishlist wishlist, BindingResult result) {
        if (result.hasErrors()) {
            return "editSuperPower";
        }
        wishlistService.editWishlist(wishlist);
        return "redirect:/superpowers";
    }

    @GetMapping("/deleteWishlistByID")
    public String deleteWishlistByID(Integer wishlistID, Model model) {
        Wishlist wishlist = wishlistService.getWishlistByID(wishlistID);
        model.addAttribute("wishlist", wishlist);
        return "deleteWishlistByID";
    }

    @PostMapping("/deleteWishlistByID")
    public String performDeleteWishlistByID(Integer wishlistID) {
        wishlistService.deleteWishlistByID(wishlistID);
        return "redirect:/wishlists";
    }
}

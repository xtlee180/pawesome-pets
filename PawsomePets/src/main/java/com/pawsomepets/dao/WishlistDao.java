package com.pawsomepets.dao;

import com.pawsomepets.dto.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
//@Transactional
@Repository
public interface WishlistDao extends JpaRepository<Wishlist, Integer> {
    @Modifying
    @Query(value = "INSERT INTO wishlist(wishlistName, userID) VALUES(?,?,?)",nativeQuery = true)
    int insertWishlist(String wishlistName, int userID);

    @Modifying
    @Query("UPDATE Wishlist SET wishlistName = :wishlistName, userID = :userID where wishlistID = :wishlistID" )
    int updateWishlist(@Param("wishlistID") int wishlistID, @Param("wishlistName") String wishlistName, @Param("userID") int userID);

    @Query("FROM Wishlist WHERE userID = :userID")
    List<Wishlist> findByUserID(@Param("userID") int userID);

}


package com.pawsomepets.dao;

import com.pawsomepets.service.dto.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface WishlistDao extends JpaRepository<Wishlist, Integer> {
    @Modifying
    @Query(value = "INSERT INTO wishlist VALUES(?,?,?)",nativeQuery = true)
    int insertWishlist(int wishlistID, int userID, int petID);
    @Modifying
    @Query("UPDATE Wishlist SET userID = :userID, petID = :petID where wishlistID = :wishlistID" )
    int updateWishlist(@Param("wishlistID") int wishlistID, @Param("userID") int userID, @Param("petID") int petID);
}

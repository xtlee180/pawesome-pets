package com.pawsomepets.dao;

import com.pawsomepets.dto.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface WishlistDao extends JpaRepository<Wishlist, Integer> {

    // returns a list of userID from DB
    @Query("FROM Wishlist WHERE userID = :userID")
    List<Wishlist> findByUserID(@Param("userID") int userID);
}


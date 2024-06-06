package com.rentme.app.review.repository;

import com.rentme.app.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(name="Review.findByUserIdAndProductId")
    Optional<Review> findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query(name="Review.findByProductId")
    List<Review> findByProductId(@Param("productId") Long productId);

    @Query(name="Review.findByUserId")
    List<Review> findByUserId(@Param("userId") Long userId);

}
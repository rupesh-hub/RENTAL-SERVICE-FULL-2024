package com.rentme.app.wish.repository;

import com.rentme.app.wish.entity.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query(name="Wish.findByProductIdAndUserId")
    Optional<Wish> findByProductIdAndUsername(@Param("productId") String productId, @Param("username") String username);

    @Query(name="Wish.findByUserId")
    Page<Wish> findByUserId(@Param("userId") String userId, Pageable pageable);

}

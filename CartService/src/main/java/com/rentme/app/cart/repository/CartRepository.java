package com.rentme.app.cart.repository;

import com.rentme.app.cart.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(name="Cart.findByProductIdAndUserId")
    Optional<Cart> findByProductIdAndUserId(@Param("productId") String productId, @Param("userId") String userId);

    @Query(name="Cart.findByUserId")
    Page<Cart> findByUserId(@Param("userId") String userId, Pageable pageable);

}
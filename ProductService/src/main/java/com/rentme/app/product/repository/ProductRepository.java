package com.rentme.app.product.repository;

import com.rentme.app.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(name="Product.findByCategory")
    Page<Product> findByCategory(@Param("category") String category);

}
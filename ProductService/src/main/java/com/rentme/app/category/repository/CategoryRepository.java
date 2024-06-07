package com.rentme.app.category.repository;

import com.rentme.app.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(name = "Category.findByName")
    Optional<Category> findByName(@Param("name") String name);

    @Query(name = "Category.findAllActive")
    List<Category> findAllActive();

}
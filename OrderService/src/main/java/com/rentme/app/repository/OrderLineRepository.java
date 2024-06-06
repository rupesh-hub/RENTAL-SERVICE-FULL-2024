package com.rentme.app.repository;

import com.rentme.app.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    @Query(value = "SELECT ol FROM OrderLine ol WHERE ol.order.id = :orderId")
    List<OrderLine> findByOrderId(@Param("orderId") String orderId);
}

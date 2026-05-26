package com.jma.orderapi.repository;

import com.jma.orderapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Conta quantos pedidos pertencem a um determinado cliente
    long countByCustomerCustomerId(Long customerId);

    // Conta quantos pedidos contêm um determinado produto na lista
    long countByProductsProductId(Long productId);
}
package com.wipro.foundation.ecommerce.order.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.foundation.ecommerce.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}

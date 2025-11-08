package com.wipro.foundation.ecommerce.order.service;
import com.wipro.foundation.ecommerce.order.entity.Order;
import java.util.List;

public interface OrderService {
    String placeOrder(Order order);
    List<Order> getAllOrders();
    String updateOrderStatus(Long orderId, String status);
}

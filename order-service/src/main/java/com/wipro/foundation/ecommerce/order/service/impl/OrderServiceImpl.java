package com.wipro.foundation.ecommerce.order.service.impl;
import com.wipro.foundation.ecommerce.order.entity.Order;
import com.wipro.foundation.ecommerce.order.entity.OrderItem;
import com.wipro.foundation.ecommerce.order.repository.OrderRepository;
import com.wipro.foundation.ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service @Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repo;
    private final RestTemplate restTemplate;

    @Value("${inventory.service.url}") private String inventoryUrl;

    public OrderServiceImpl(OrderRepository repo, RestTemplate restTemplate){
        this.repo = repo; this.restTemplate = restTemplate;
    }

    @Override
    public String placeOrder(Order order){
        order.setStatus("CREATED");
        order.setCreatedAt(Instant.now());
        // compute total
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : order.getItems()){
            BigDecimal line = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(line);
            // adjust inventory - reduce
            try {
                restTemplate.postForEntity(inventoryUrl + "/inventory/{pid}/stock/adjust?delta={delta}", null,
                        String.class, item.getProductId(), -item.getQuantity());
            } catch (Exception ex){
                // In a real system: rollback/saga. For demo, continue.
            }
        }
        order.setTotalAmount(total);
        repo.save(order);
        return "Order placed";
    }

    @Override
    public List<Order> getAllOrders(){ return repo.findAll(); }

    @Override
    public String updateOrderStatus(Long orderId, String status){
        return repo.findById(orderId).map(o->{
            String prev = o.getStatus();
            o.setStatus(status);
            if ("CANCELLED".equalsIgnoreCase(status) && !"CANCELLED".equalsIgnoreCase(prev)){
                // restock
                for (OrderItem item : o.getItems()){
                    try {
                        restTemplate.postForEntity(inventoryUrl + "/inventory/{pid}/stock/adjust?delta={delta}", null,
                                String.class, item.getProductId(), item.getQuantity());
                    } catch (Exception ignored){}
                }
            }
            repo.save(o);
            return "Order status updated";
        }).orElseThrow(()->new IllegalArgumentException("Order not found"));
    }
}

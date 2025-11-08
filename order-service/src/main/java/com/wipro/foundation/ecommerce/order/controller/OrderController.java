package com.wipro.foundation.ecommerce.order.controller;
import com.wipro.foundation.ecommerce.order.entity.Order;
import com.wipro.foundation.ecommerce.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController @RequestMapping("/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<?> place(@RequestBody Order order){
        String msg = service.placeOrder(order);
        return ResponseEntity.created(URI.create("/orders")).body(msg);
    }
    @GetMapping
    public ResponseEntity<?> list(){ return ResponseEntity.ok(service.getAllOrders()); }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam("value") String value){
        return ResponseEntity.ok(service.updateOrderStatus(id, value));
    }
}

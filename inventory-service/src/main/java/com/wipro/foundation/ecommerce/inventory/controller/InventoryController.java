package com.wipro.foundation.ecommerce.inventory.controller;
import com.wipro.foundation.ecommerce.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService service;
    public InventoryController(InventoryService service){ this.service = service; }

    @GetMapping("/{productId}/stock")
    public ResponseEntity<?> stock(@PathVariable Long productId){
        return ResponseEntity.ok(service.getStockLevel(productId));
    }
    @PutMapping("/{productId}/stock")
    public ResponseEntity<?> update(@PathVariable Long productId, @RequestParam int quantity){
        return ResponseEntity.ok(service.updateStock(productId, quantity));
    }
    @PostMapping("/{productId}/stock/adjust")
    public ResponseEntity<?> adjust(@PathVariable Long productId, @RequestParam int delta){
        return ResponseEntity.ok(service.adjustStock(productId, delta));
    }
}

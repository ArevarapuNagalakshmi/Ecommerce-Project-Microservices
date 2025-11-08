package com.wipro.foundation.ecommerce.inventory.service;
public interface InventoryService {
    int getStockLevel(Long productId);
    String updateStock(Long productId, int quantity); // sets stock to quantity
    String adjustStock(Long productId, int delta);    // +/- change
}

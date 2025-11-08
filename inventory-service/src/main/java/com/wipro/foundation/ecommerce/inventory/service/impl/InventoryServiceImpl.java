package com.wipro.foundation.ecommerce.inventory.service.impl;
import com.wipro.foundation.ecommerce.inventory.entity.Inventory;
import com.wipro.foundation.ecommerce.inventory.repository.InventoryRepository;
import com.wipro.foundation.ecommerce.inventory.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository repo;
    public InventoryServiceImpl(InventoryRepository repo){ this.repo = repo; }

    @Override
    public int getStockLevel(Long productId){
        return repo.findById(productId).map(Inventory::getStock).orElse(0);
    }
    @Override
    public String updateStock(Long productId, int quantity){
        Inventory inv = repo.findById(productId).orElse(Inventory.builder().productId(productId).stock(0).build());
        inv.setStock(quantity);
        repo.save(inv);
        return "Stock updated";
    }
    @Override
    public String adjustStock(Long productId, int delta){
        Inventory inv = repo.findById(productId).orElse(Inventory.builder().productId(productId).stock(0).build());
        int newStock = Math.max(0, inv.getStock() + delta);
        inv.setStock(newStock);
        repo.save(inv);
        return "Stock adjusted";
    }
}

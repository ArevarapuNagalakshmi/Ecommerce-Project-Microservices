package com.wipro.foundation.ecommerce.inventory.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.foundation.ecommerce.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {}

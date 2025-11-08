package com.wipro.foundation.ecommerce.product.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.foundation.ecommerce.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}

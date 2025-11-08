package com.wipro.foundation.ecommerce.product.service;
import com.wipro.foundation.ecommerce.product.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product add(Product p);
    Product update(Long id, Product p);
    void delete(Long id);
    Optional<Product> get(Long id);
    List<Product> getAll();
}

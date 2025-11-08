package com.wipro.foundation.ecommerce.product.controller;
import com.wipro.foundation.ecommerce.product.entity.Product;
import com.wipro.foundation.ecommerce.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController @RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service){ this.service = service; }

    @PostMapping public ResponseEntity<?> add(@RequestBody Product p){
        Product saved = service.add(p);
        return ResponseEntity.created(URI.create("/products/"+saved.getId())).body(saved);
    }
    @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product p){
        return ResponseEntity.ok(service.update(id, p));
    }
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id); return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable Long id){
        return service.get(id).<ResponseEntity<?>>map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping public ResponseEntity<?> list(){ return ResponseEntity.ok(service.getAll()); }
}

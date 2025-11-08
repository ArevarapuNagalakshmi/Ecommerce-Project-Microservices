package com.wipro.foundation.ecommerce.customer.controller;
import com.wipro.foundation.ecommerce.customer.entity.Customer;
import com.wipro.foundation.ecommerce.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer c){
        Customer saved = service.create(c);
        return ResponseEntity.created(URI.create("/customers/"+saved.getId())).body(saved);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        return service.get(id).<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<?> list(){ return ResponseEntity.ok(service.getAll()); }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer c){
        return ResponseEntity.ok(service.update(id, c));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id); return ResponseEntity.noContent().build();
    }
}

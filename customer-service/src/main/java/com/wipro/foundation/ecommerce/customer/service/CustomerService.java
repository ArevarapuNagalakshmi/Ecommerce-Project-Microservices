package com.wipro.foundation.ecommerce.customer.service;
import com.wipro.foundation.ecommerce.customer.entity.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer create(Customer c);
    Optional<Customer> get(Long id);
    List<Customer> getAll();
    Customer update(Long id, Customer c);
    void delete(Long id);
}

package com.wipro.foundation.ecommerce.customer.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.foundation.ecommerce.customer.entity.Customer;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);
}

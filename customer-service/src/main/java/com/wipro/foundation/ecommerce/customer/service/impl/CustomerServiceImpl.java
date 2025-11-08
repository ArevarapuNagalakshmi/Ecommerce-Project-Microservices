package com.wipro.foundation.ecommerce.customer.service.impl;
import com.wipro.foundation.ecommerce.customer.entity.Customer;
import com.wipro.foundation.ecommerce.customer.repository.CustomerRepository;
import com.wipro.foundation.ecommerce.customer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service @Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repo;
    public CustomerServiceImpl(CustomerRepository repo){ this.repo = repo; }

    @Override
    public Customer create(Customer c) {
        if (repo.existsByEmail(c.getEmail())) throw new IllegalArgumentException("Customer already exists for email");
        return repo.save(c);
    }
    @Override public Optional<Customer> get(Long id){ return repo.findById(id); }
    @Override public List<Customer> getAll(){ return repo.findAll(); }
    @Override
    public Customer update(Long id, Customer c){
        return repo.findById(id).map(e->{
            e.setName(c.getName());
            e.setPhone(c.getPhone());
            if(!e.getEmail().equals(c.getEmail())){
                if(repo.existsByEmail(c.getEmail())) throw new IllegalArgumentException("Email in use");
                e.setEmail(c.getEmail());
            }
            return repo.save(e);
        }).orElseThrow(()->new IllegalArgumentException("Customer not found"));
    }
    @Override public void delete(Long id){ repo.deleteById(id); }
}

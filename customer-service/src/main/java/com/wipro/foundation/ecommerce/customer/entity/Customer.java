package com.wipro.foundation.ecommerce.customer.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="customers")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String email;
    @Column(nullable=false)
    private String name;
    private String phone;
}

package com.wipro.foundation.ecommerce.inventory.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="inventory")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Inventory {
    @Id
    private Long productId;
    private Integer stock;
}

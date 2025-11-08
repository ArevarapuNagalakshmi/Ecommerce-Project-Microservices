package com.wipro.foundation.ecommerce.common.dto;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderDTO {
    private Long id;
    private Long customerId;
    private List<OrderItemDTO> items;
    private String status; // CREATED, SHIPPED, CANCELLED
    private BigDecimal totalAmount;
    private Instant createdAt;
}

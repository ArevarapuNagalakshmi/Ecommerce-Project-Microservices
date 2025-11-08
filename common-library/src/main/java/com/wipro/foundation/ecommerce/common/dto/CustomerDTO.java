package com.wipro.foundation.ecommerce.common.dto;
import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<OrderDTO> orders; // optional aggregation
}

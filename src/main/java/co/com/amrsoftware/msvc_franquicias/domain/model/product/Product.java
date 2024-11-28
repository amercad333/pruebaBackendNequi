package co.com.amrsoftware.msvc_franquicias.domain.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class Product {
    private Long id;
    private String productName;
    private Integer quantity;
    private LocalDateTime createAt;
    private LocalDateTime updateAp;
    private boolean status;
}

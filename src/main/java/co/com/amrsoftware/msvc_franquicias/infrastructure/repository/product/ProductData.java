package co.com.amrsoftware.msvc_franquicias.infrastructure.repository.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@Table(value = "products")
public class ProductData {
    @Id
    private Long id;
    @Column(value = "product_name")
    private String productName;
    private Integer quantity;
    @Column(value = "create_at")
    private LocalDateTime createAt;
    @Column(value = "update_at")
    private LocalDateTime updateAp;
    private boolean status;
}

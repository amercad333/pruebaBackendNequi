package co.com.amrsoftware.msvc_franquicias.infrastructure.repository.brancheproduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@Table(value = "branche_product")
public class BrancheProductData {
    @Id
    private Long id;
    @Column(value = "branche_id")
    private Long brancheId;
    @Column(value = "product_id")
    private Long productId;
}

package co.com.amrsoftware.msvc_franquicias.domain.model.brancheproduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class BrancheProduct {
    private Long id;
    private Long brancheId;
    private Long productId;
}

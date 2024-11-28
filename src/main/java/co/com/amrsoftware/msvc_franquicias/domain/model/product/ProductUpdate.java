package co.com.amrsoftware.msvc_franquicias.domain.model.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class ProductUpdate {
    @Min(1)
    @NotNull
    private Integer quantity;
    private LocalDateTime updateAp;
}

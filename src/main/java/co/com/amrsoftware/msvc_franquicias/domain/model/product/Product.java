package co.com.amrsoftware.msvc_franquicias.domain.model.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class Product {
    private Long id;
    @NotBlank
    private String productName;
    @Min(1)
    @NotNull
    private Integer quantity;
    private LocalDateTime createAt;
    private LocalDateTime updateAp;
    private boolean status;
}

package co.com.amrsoftware.msvc_franquicias.domain.model.branche;

import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class BrancheAdd {
    @NotBlank
    private String brancheName;
    @Valid
    @NotEmpty
    private List<Product> products;

    public BrancheAdd() {
        products = new ArrayList<>();
    }
}

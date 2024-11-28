package co.com.amrsoftware.msvc_franquicias.domain.model.branche;

import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class Branche {
    private Long id;
    private String brancheName;
    private LocalDateTime createAt;
    private LocalDateTime updateAp;
    private boolean status;
    private List<Product> products;

    public Branche() {
        products = new ArrayList<>();
    }
}

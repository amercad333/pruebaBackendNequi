package co.com.amrsoftware.msvc_franquicias.infrastructure.repository.product;

import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductDataRepository extends ReactiveCrudRepository<ProductData, Long> {
    @Modifying
    Mono<Void> deleteById(Long id);

    Mono<Product> saveAll(List<Product> products);
}

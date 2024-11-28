package co.com.amrsoftware.msvc_franquicias.domain.model.product.gateways;

import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Flux<Product> findAll();
    Mono<Product> findById(Long id);
    Mono<Product> save(Product product);
    Mono<Void> deleteById(Long id);
}

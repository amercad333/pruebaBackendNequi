package co.com.amrsoftware.msvc_franquicias.domain.model.product.gateways;

import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductRepository {
    Flux<Product> findAll();
    Flux<Product> findAllById(List<Long> ids);
    Mono<Product> findById(Long id);
    Mono<Product> save(Product product);
    Flux<Product> saveAll(List<Product> products);
    Mono<Void> deleteById(Long id);
}

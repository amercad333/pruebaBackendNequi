package co.com.amrsoftware.msvc_franquicias.domain.usecase.product;

import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import co.com.amrsoftware.msvc_franquicias.domain.model.product.gateways.ProductRepository;
import co.com.amrsoftware.msvc_franquicias.domain.usecase.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ProductUseCase {
    private final ProductRepository repository;

    private static final String MESSAGE_OBJECT_NOT_FOUND = "The product could not be found";

    public Flux<Product> findAll() {
        return repository.findAll();
    }

    public Mono<Product> findById(Long id) {
        return repository.findById(id).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
        );
    }

    public Mono<Product> save(Product product) {
        product.setId(null);
        product.setStatus(Boolean.TRUE);
        product.setCreateAt(LocalDateTime.now());
        return repository.save(product);
    }

    public Mono<Product> updateById(Product product, Long id) {
        return repository.findById(id)
        .flatMap(productDB -> {
            productDB.setProductName(product.getProductName());
            productDB.setQuantity(product.getQuantity());
            productDB.setUpdateAp(LocalDateTime.now());
            return repository.save(productDB);
        }).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
        );
    }

    public Mono<Void> deleteById(Long id) {
        return repository.findById(id)
        .flatMap(productDB -> repository.deleteById(id)).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
        ).then();
    }

}

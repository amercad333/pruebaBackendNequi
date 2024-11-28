package co.com.amrsoftware.msvc_franquicias.domain.usecase.product;

import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import co.com.amrsoftware.msvc_franquicias.domain.model.product.ProductUpdate;
import co.com.amrsoftware.msvc_franquicias.domain.model.product.gateways.ProductRepository;
import co.com.amrsoftware.msvc_franquicias.domain.usecase.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

import static co.com.amrsoftware.msvc_franquicias.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_FOUND;

@RequiredArgsConstructor
public class ProductUseCase {
    private final ProductRepository repository;

    public Flux<Product> findAll() {
        return repository.findAll();
    }

    public Flux<Product> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public Flux<Product> saveAll(List<Product> products) {
        var productsData = products.stream().map(product -> {
            product.setId(null);
            product.setStatus(Boolean.TRUE);
            product.setCreateAt(LocalDateTime.now());
            return product;
        }).toList();
        return repository.saveAll(productsData);
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

    public Mono<Product> updateById(ProductUpdate product, Long id) {
        return repository.findById(id)
        .flatMap(productDB -> {
            productDB.setQuantity(product.getQuantity());
            productDB.setUpdateAp(LocalDateTime.now());
            return repository.save(productDB);
        }).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
        );
    }

    public Mono<Void> deleteById(Long id) {
        return repository.findById(id)
        .flatMap(productDB -> {
            repository.deleteById(id).subscribe();
            return Mono.just(true);
        })
        .switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
        ).then();
    }

}

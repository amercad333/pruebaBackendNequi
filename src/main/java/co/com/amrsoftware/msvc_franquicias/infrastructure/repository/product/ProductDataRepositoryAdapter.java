package co.com.amrsoftware.msvc_franquicias.infrastructure.repository.product;

import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import co.com.amrsoftware.msvc_franquicias.domain.model.product.gateways.ProductRepository;
import co.com.amrsoftware.msvc_franquicias.infrastructure.repository.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductDataRepositoryAdapter implements ProductRepository {
    private final ProductDataRepository repository;
    private final ProductMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Flux<Product> findAll() {
        return repository.findAll().filter(ProductData::isStatus).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Product> findById(Long id) {
        return repository.findById(id).filter(ProductData::isStatus).map(mapper::toDto);
    }

    @Override
    @Transactional
    public Mono<Product> save(Product product) {
        return repository.save(mapper.toEntity(product))
            .filter(ProductData::isStatus).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}

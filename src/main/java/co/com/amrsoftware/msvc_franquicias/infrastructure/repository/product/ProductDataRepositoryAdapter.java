package co.com.amrsoftware.msvc_franquicias.infrastructure.repository.product;

import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import co.com.amrsoftware.msvc_franquicias.domain.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDataRepositoryAdapter implements ProductRepository {
    private final ProductDataRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Flux<Product> findAll() {
        return repository.findAll().filter(ProductData::isStatus).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Product> findAllById(List<Long> ids) {
        return repository.findAllById(ids).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Product> findById(Long id) {
        return repository.findById(id).filter(ProductData::isStatus).map(this::toDto);
    }

    @Override
    @Transactional
    public Mono<Product> save(Product product) {
        return repository.save(this.toEntity(product))
            .filter(ProductData::isStatus).map(this::toDto);
    }

    @Override
    @Transactional
    public Flux<Product> saveAll(List<Product> products) {
        var productData = products.stream().map(this::toEntity).toList();
        return repository.saveAll(productData).map(this::toDto);
    }

    @Transactional
    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    private Product toDto(ProductData data) {
        return Product.builder()
            .id(data.getId())
            .productName(data.getProductName())
            .quantity(data.getQuantity())
            .createAt(data.getCreateAt())
            .updateAp(data.getUpdateAp())
            .status(data.isStatus())
            .build();
    }

    private ProductData toEntity(Product data) {
        return ProductData.builder()
            .id(data.getId())
            .productName(data.getProductName())
            .quantity(data.getQuantity())
            .createAt(data.getCreateAt())
            .updateAp(data.getUpdateAp())
            .status(data.isStatus())
            .build();
    }
}

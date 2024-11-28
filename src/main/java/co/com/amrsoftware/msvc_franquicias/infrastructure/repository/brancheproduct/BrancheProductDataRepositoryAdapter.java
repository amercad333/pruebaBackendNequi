package co.com.amrsoftware.msvc_franquicias.infrastructure.repository.brancheproduct;

import co.com.amrsoftware.msvc_franquicias.domain.model.brancheproduct.BrancheProduct;
import co.com.amrsoftware.msvc_franquicias.domain.model.brancheproduct.gateways.BrancheProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class BrancheProductDataRepositoryAdapter implements BrancheProductRepository {
    private final BrancheProductDataRepository repository;

    @Override
    public Mono<BrancheProduct> save(BrancheProduct brancheProduct) {
        return repository.save(this.toEntity(brancheProduct)).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Boolean> existsByBrancheIdAndProductId(Long brancheId, Long productId) {
        return repository.existsByBrancheIdAndProductId(brancheId, productId);
    }

    @Override
    public Flux<BrancheProduct> findByBrancheId(Long brancheId) {
        return repository.findByBrancheId(brancheId).map(this::toDto);
    }

    @Override
    public Flux<BrancheProduct> findByProductId(Long roleId) {
        return null;
    }

    @Override
    public Mono<Void> deleteByBrancheId(Long roleId) {
        return null;
    }

    @Override
    public Mono<Void> deleteByProductId(Long userId) {
        return null;
    }

    private BrancheProduct toDto(BrancheProductData data) {
        return BrancheProduct.builder()
            .id(data.getId())
            .brancheId(data.getBrancheId())
            .productId(data.getProductId())
            .build();
    }

    private BrancheProductData toEntity(BrancheProduct data) {
        return BrancheProductData.builder()
                .id(data.getId())
                .brancheId(data.getBrancheId())
                .productId(data.getProductId())
                .build();
    }
}

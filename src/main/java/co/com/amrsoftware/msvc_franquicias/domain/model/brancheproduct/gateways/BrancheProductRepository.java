package co.com.amrsoftware.msvc_franquicias.domain.model.brancheproduct.gateways;

import co.com.amrsoftware.msvc_franquicias.domain.model.brancheproduct.BrancheProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BrancheProductRepository {
    Mono<BrancheProduct> save(BrancheProduct brancheProduct);
    Mono<Boolean> existsByBrancheIdAndProductId(Long brancheId, Long productId);
    Flux<BrancheProduct> findByBrancheId(Long brancheId);
    Flux<BrancheProduct> findByProductId(Long roleId);
    Mono<Void> deleteByBrancheId(Long roleId);
    Mono<Void> deleteByProductId(Long userId);
}

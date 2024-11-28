package co.com.amrsoftware.msvc_franquicias.infrastructure.repository.brancheproduct;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BrancheProductDataRepository extends ReactiveCrudRepository<BrancheProductData, Long> {
    Mono<Boolean> existsByBrancheIdAndProductId(Long brancheId, Long productId);
    Flux<BrancheProductData> findByBrancheId(Long brancheId);
}

package co.com.amrsoftware.msvc_franquicias.domain.model.branche.gateways;

import co.com.amrsoftware.msvc_franquicias.domain.model.branche.Branche;
import reactor.core.publisher.Mono;

public interface BrancheRepository {
    Mono<Branche> findById(Long id);
    Mono<Branche> save(Branche branche);
}

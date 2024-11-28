package co.com.amrsoftware.msvc_franquicias.domain.usecase.brancheproduct;


import co.com.amrsoftware.msvc_franquicias.domain.model.brancheproduct.BrancheProduct;
import co.com.amrsoftware.msvc_franquicias.domain.model.brancheproduct.gateways.BrancheProductRepository;
import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import co.com.amrsoftware.msvc_franquicias.domain.usecase.exception.ObjectNotExistingException;
import co.com.amrsoftware.msvc_franquicias.domain.usecase.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.com.amrsoftware.msvc_franquicias.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_EXISTING;
import static co.com.amrsoftware.msvc_franquicias.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_FOUND_BRANCHE;

@RequiredArgsConstructor
public class BrancheProductUseCase {
    private final BrancheProductRepository repository;

    public Mono<BrancheProduct> assignProduct(Long brancheId, Long productId) {

        return repository.existsByBrancheIdAndProductId(brancheId, productId).flatMap(existsIds -> {
            if ( existsIds ) {
                throw new ObjectNotExistingException(MESSAGE_OBJECT_NOT_EXISTING);
            }

            var brancheProduct = BrancheProduct.builder()
                .brancheId(brancheId)
                .productId(productId)
                .build();
            return repository.save(brancheProduct);
        });

    }

    public Flux<BrancheProduct> findByBrancheId(Long brancheId) {
        return repository.findByBrancheId(brancheId).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND_BRANCHE))
        );

    }
}

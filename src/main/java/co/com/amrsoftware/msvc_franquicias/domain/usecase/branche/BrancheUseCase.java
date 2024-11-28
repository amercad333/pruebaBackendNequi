package co.com.amrsoftware.msvc_franquicias.domain.usecase.branche;

import co.com.amrsoftware.msvc_franquicias.domain.model.branche.Branche;
import co.com.amrsoftware.msvc_franquicias.domain.model.branche.BrancheAdd;
import co.com.amrsoftware.msvc_franquicias.domain.model.branche.gateways.BrancheRepository;
import co.com.amrsoftware.msvc_franquicias.domain.model.brancheproduct.BrancheProduct;
import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import co.com.amrsoftware.msvc_franquicias.domain.usecase.brancheproduct.BrancheProductUseCase;
import co.com.amrsoftware.msvc_franquicias.domain.usecase.exception.ObjectNotFoundException;
import co.com.amrsoftware.msvc_franquicias.domain.usecase.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static co.com.amrsoftware.msvc_franquicias.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_FOUND;
import static co.com.amrsoftware.msvc_franquicias.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_FOUND_BRANCHE;

@RequiredArgsConstructor
public class BrancheUseCase {
    private final BrancheRepository repository;
    private final ProductUseCase productUseCase;
    private final BrancheProductUseCase useCase;

    public Mono<Branche> assignProduct(Long brancheId, Long productId) {
        return repository.findById(brancheId).flatMap(branche -> {
            return productUseCase.findById(productId).flatMap(productDB -> {
                return useCase.assignProduct(brancheId, productId).map(data -> branche);
            }).switchIfEmpty(
                Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
            );
        }).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND_BRANCHE))
        );
    }

    public Mono<Branche> createProduct(BrancheAdd brancheAdd) {
        var branche = createNewBranche(brancheAdd);

        return repository.save(branche).flatMap(brancheDB -> {
          return productUseCase.saveAll(brancheAdd.getProducts()).collectList().map(products -> {
            branche.setId(brancheDB.getId());
            branche.setUpdateAt(brancheDB.getUpdateAt());
            branche.setProducts(products);

            products.forEach(product -> {
                useCase.assignProduct(brancheDB.getId(), product.getId()).subscribe();
            });

            return branche;
          });



        });
    }

    private static Branche createNewBranche(BrancheAdd brancheAdd) {
        return Branche.builder()
                .brancheName(brancheAdd.getBrancheName())
                .createAt(LocalDateTime.now())
                .status(Boolean.TRUE)
                .build();
    }

    public Mono<Branche> findById(Long id) {
        return repository.findById(id)
        .flatMap(brancheDB -> {
            return useCase.findByBrancheId(id).collectList().flatMap(brancheProduct -> {
                return productUseCase.findAllById(
                    brancheProduct.stream().map(BrancheProduct::getProductId).toList()
                ).collectList().map(products -> {
                    return Branche.builder()
                            .id(brancheDB.getId())
                            .brancheName(brancheDB.getBrancheName())
                            .createAt(brancheDB.getCreateAt())
                            .updateAt(brancheDB.getUpdateAt())
                            .status(brancheDB.isStatus())
                            .products(products)
                            .build();
                });

            });
        }).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND_BRANCHE))
        );
    }


}

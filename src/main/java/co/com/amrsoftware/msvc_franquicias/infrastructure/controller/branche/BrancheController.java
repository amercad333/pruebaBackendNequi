package co.com.amrsoftware.msvc_franquicias.infrastructure.controller.branche;

import co.com.amrsoftware.msvc_franquicias.domain.model.branche.Branche;
import co.com.amrsoftware.msvc_franquicias.domain.model.branche.BrancheAdd;
import co.com.amrsoftware.msvc_franquicias.domain.usecase.branche.BrancheUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static co.com.amrsoftware.msvc_franquicias.infrastructure.controller.util.constant.Constant.API;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branches")
public class BrancheController {
    private final BrancheUseCase useCase;

    @GetMapping(API + "/{id}")
    public ResponseEntity<Mono<Branche>> findShow(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.findById(id));
    }

    @PostMapping(API)
    public ResponseEntity<Mono<Branche>> save(@Valid @RequestBody BrancheAdd branche) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.createProduct(branche));
    }

    @PutMapping(API + "/assign/{brancheId}/{productId}")
    public ResponseEntity<Mono<Branche>> assignProduct(@PathVariable Long brancheId, @PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.assignProduct(brancheId, productId));
    }
}

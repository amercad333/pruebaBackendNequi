package co.com.amrsoftware.msvc_franquicias.infrastructure.repository.branche;

import co.com.amrsoftware.msvc_franquicias.domain.model.branche.Branche;
import co.com.amrsoftware.msvc_franquicias.domain.model.branche.gateways.BrancheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BrancheDataRepositoryAdapter implements BrancheRepository {
    private final BrancheDataRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Mono<Branche> findById(Long id) {
        return repository.findById(id).filter(BrancheData::isStatus).map(this::toDto);
    }

    @Override
    @Transactional
    public Mono<Branche> save(Branche branche) {
        return repository.save(this.toEntity(branche)).map(this::toDto);
    }

    private Branche toDto(BrancheData data) {
        return Branche.builder()
                .id(data.getId())
                .brancheName(data.getBrancheName())
                .createAt(data.getCreateAt())
                .updateAt(data.getUpdateAt())
                .status(data.isStatus())
                .build();
    }

    private BrancheData toEntity(Branche data) {
        return BrancheData.builder()
            .id(data.getId())
            .brancheName(data.getBrancheName())
            .createAt(data.getCreateAt())
            .updateAt(data.getUpdateAt())
            .status(data.isStatus())
            .build();
    }
}

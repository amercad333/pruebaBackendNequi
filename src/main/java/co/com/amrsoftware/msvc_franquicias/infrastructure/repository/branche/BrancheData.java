package co.com.amrsoftware.msvc_franquicias.infrastructure.repository.branche;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Table(value = "branches")
public class BrancheData {
    @Id
    private Long id;
    @Column(value = "branche_name")
    private String brancheName;
    @Column(value = "create_at")
    private LocalDateTime createAt;
    @Column(value = "update_at")
    private LocalDateTime updateAt;
    private boolean status;
}

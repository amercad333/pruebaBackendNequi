package co.com.amrsoftware.msvc_franquicias.domain.model.franchise;

import co.com.amrsoftware.msvc_franquicias.domain.model.branche.Branche;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class Franchise {
    private Long id;
    private String franchiseName;
    private LocalDateTime createAt;
    private LocalDateTime updateAp;
    private boolean status;

    private List<Branche> branches;

    public Franchise() {
        branches = new ArrayList<>();
    }
}

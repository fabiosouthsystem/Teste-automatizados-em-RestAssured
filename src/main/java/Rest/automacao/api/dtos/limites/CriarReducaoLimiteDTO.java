package Rest.automacao.api.dtos.limites;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CriarReducaoLimiteDTO {

    private Integer amount;
    private String origin;
    private IdentificacaoDTO executedBy;
    private IdentificacaoDTO requestedBy;
    private Integer totalLimit;
}

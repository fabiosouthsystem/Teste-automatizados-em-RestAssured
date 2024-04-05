package Rest.automacao.api.dtos.limites;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CriarMajoracaoLimiteDTO {

    private Integer amount;
    private Integer proposalValue;
    private Integer availableCredit;
    private String origin;
    private String limitReservedValue;
    private IdentificacaoDTO executedBy;
    private IdentificacaoDTO requestedBy;
    private IdentificacaoDTO availableTotalLimit;
}

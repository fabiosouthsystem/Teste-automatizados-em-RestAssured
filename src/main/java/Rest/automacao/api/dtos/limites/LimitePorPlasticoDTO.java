package Rest.automacao.api.dtos.limites;

import lombok.Data;

@Data
public class LimitePorPlasticoDTO {

    private Integer plasticId;
    private Integer totalLimit;
    private Integer availableTotalLimit;
    private Integer userLimit;
    private Integer availableCredit;
    private Integer usedCredit;
    private Integer withdrawalLimit;
    private Integer availableWithdrawal;
}

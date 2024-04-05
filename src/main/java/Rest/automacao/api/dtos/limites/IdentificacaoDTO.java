package Rest.automacao.api.dtos.limites;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IdentificacaoDTO {
    private String name;
    private String ldap;
    private String suid;
}

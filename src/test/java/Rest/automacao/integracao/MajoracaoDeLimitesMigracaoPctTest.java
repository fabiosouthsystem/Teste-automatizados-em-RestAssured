package Rest.automacao.integracao;

import io.sicredi.cartoeslimite.automacao.api.client.ContaCartaoClient;
import Rest.automacao.api.client.LimitesClient;
import Rest.automacao.api.dtos.limites.CriarMajoracaoLimiteDTO;
import Rest.automacao.api.dtos.limites.IdentificacaoDTO;
import Rest.automacao.api.util.EnvironmentRuntimeLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled // execução manual somente
class MajoracaoDeLimitesMigracaoPctTest {

    static final Integer ID_CONTA = 66093026;
    static final Integer ID_PLASTICO = 914958;

    /*@Test
    void obterLimitePorPlastico() throws Exception {
        String environment = EnvironmentRuntimeLoader.get();
        var limitesClient = new LimitesClient(environment);
        limitesClient.obterLimitePorPlastico(ID_PLASTICO);
    }*/

    @Test
    void criarMajoracaoPorAssociado() throws Exception {
        String environment = EnvironmentRuntimeLoader.get();

        var limitesClient = new LimitesClient(environment);
        var contaCartaoClient = new ContaCartaoClient(environment);
        // mocka o valor do limite total da conta para sempre iniciar um valor fixo//
        contaCartaoClient.alterarLimiteTotalConta(ID_CONTA, 150000);

        var criarMajoracaoLimiteDTO = CriarMajoracaoLimiteDTO.builder()
                .amount(224900)
                .proposalValue(225000)
                .executedBy(IdentificacaoDTO.builder()
                        .ldap("daysin_aguillar")
                        .build())
                .requestedBy(IdentificacaoDTO.builder()
                        .suid("150212367333")
                        .build())
                .build();

        // executa a majoracao de limite por associado //
        limitesClient.criarMajoracaoLimitePorAssociado(ID_PLASTICO, criarMajoracaoLimiteDTO);

        var limiteTotalConta = contaCartaoClient.obterLimiteTotalContaMajorada(ID_PLASTICO);
        // verifica se limite total conta foi realmente alterado //
        /*assert Objects.equals(limiteTotalConta, "225000");*/
    }
}
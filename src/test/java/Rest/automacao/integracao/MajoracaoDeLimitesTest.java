package Rest.automacao.integracao;

import io.sicredi.cartoeslimite.automacao.api.client.ContaCartaoClient;
import Rest.automacao.api.client.LimitesClient;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

class MajoracaoDeLimitesTest {

    static final Integer ID_CONTA = 913405;
    static final Integer ID_PLASTICO = 47972;
    static final Integer CREDIT_VALUE = 900000;
    private final ContaCartaoClient contaCartaoClient = new ContaCartaoClient();
    private final LimitesClient limitesClient = new LimitesClient();
    @Test
    void obterLimitePorPlastico() throws Exception {
        var response = limitesClient.obterLimitePorPlastico(ID_PLASTICO);

        response.assertThat()
                .statusCode(200);
    }

    @Test
    void criarMajoracaoPorAssociado() throws Exception {
        var response = contaCartaoClient.alterarLimiteTotalConta(ID_CONTA, CREDIT_VALUE);
        // Majorando o valor do crédito em 1000
        int majoraLimite = CREDIT_VALUE + 100000;

        response = contaCartaoClient.alterarLimiteTotalConta(ID_CONTA, majoraLimite);
        response
                .assertThat().statusCode(200)
                .body("data.limitReservedValue", equalTo(1000000));

    }
}
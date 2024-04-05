package Rest.automacao.integracao;

import io.sicredi.cartoeslimite.automacao.api.client.ContaCartaoClient;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;


class IsencaoDeAnuidadeTest {

    private static final String ID_CONTA = "913405";

    private final ContaCartaoClient contaCartaoClient = new ContaCartaoClient();

    @Test
    void cancelaIsencao() throws Exception {
        var response = contaCartaoClient.obterIsencaoAnuidade(ID_CONTA, "VIGENTE");

        var idIsencaoVigente = response.extract().jsonPath().getString("[0].id");
        if (StringUtils.isNotEmpty(idIsencaoVigente)) {

            var cancelarResponse = contaCartaoClient.cancelarIsencao(idIsencaoVigente);

            cancelarResponse
                    .assertThat().statusCode(200)
                    .assertThat().body("status", equalTo("CANCELADO"));
        }
    }

    @Test
    void confirmaIsencao() throws Exception {
        var response = contaCartaoClient.confirmarIsencao(ID_CONTA);

        response.assertThat()
                .statusCode(200)
                .body("status", equalTo("VIGENTE"));
    }
}
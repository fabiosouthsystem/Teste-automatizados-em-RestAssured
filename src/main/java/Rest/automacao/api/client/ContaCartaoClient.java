package Rest.automacao.api.client

-automatizados-em-Rest.automacao;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import Rest.automacao.api.util.EnvironmentRuntimeLoader;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static testes.automatizados.em.rest.automacao.api.factory.RequestSpecificationFactory.*;

public class ContaCartaoClient {
    private final String environment;

    public ContaCartaoClient(String environment) {
        this.environment = environment;
    }

    public ContaCartaoClient() {
        this(EnvironmentRuntimeLoader.get());
    }

    public ValidatableResponse alterarLimiteTotalConta(Integer idConta, Integer valor) {
        return given()
                .pathParam("idConta", idConta)
                .spec(requestSpecificationCardExtensionPlastic(environment))
                .contentType(ContentType.JSON)
                .body("{" +
                        " \"limitReservedValue\": \"" + valor + "\"" +
                        "}")
                .when()
                .patch("/cardAccounts/{idConta}")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public String obterLimiteTotalContaMajorada(Integer plasticId) {
        return given()
                .pathParam("plasticId", plasticId)
                .spec(requestSpecificationLimites(environment))
                .contentType(ContentType.JSON)
                .when()
                .get("/limits/{plasticId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("availableTotalLimit").toString();
    }

    public ValidatableResponse confirmarIsencao(String contaId) {
        return given()
                .pathParam("contaId", contaId)
                .spec(requestSpecificationCardsInvoiceCharge())
                .contentType(ContentType.JSON)
                .body("{" +
                        "    \"tipoDuracao\": \"EM_MESES\"," +
                        "    \"duracao\": 4" +
                        "}")
                .when()
                .post("/isencao-anuidade/conta-cartao/{contaId}/confirmar")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse cancelarIsencao(String id) {
        return given()
                .pathParam("id", id)
                .spec(requestSpecificationCardsInvoiceCharge())
                .contentType(ContentType.JSON)
                .when()
                .put("/isencao-anuidade/{id}/cancelar")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse obterIsencaoAnuidade(String contaId, String status) {
        return given()
                .queryParam("contaId", contaId)
                .queryParam("status", status)
                .spec(requestSpecificationCardsInvoiceCharge())
                .contentType(ContentType.JSON)
                .when()
                .get("/isencao-anuidade")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
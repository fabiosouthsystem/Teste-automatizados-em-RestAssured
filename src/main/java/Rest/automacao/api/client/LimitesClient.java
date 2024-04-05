package Rest.automacao.api.client;

import Rest.automacao.api.dtos.limites.CriarMajoracaoLimiteDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import Rest.automacao.api.util.EnvironmentRuntimeLoader;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static Rest.automacao.api.factory.RequestSpecificationFactory.requestSpecificationLimites;

public class LimitesClient {
    private final String environment;

    public LimitesClient(String environment) {
        this.environment = environment;
    }

    public LimitesClient() {
        this(EnvironmentRuntimeLoader.get());
    }


    public ValidatableResponse obterLimitePorPlastico(Integer idPlastico) {
        return given()
                .pathParam("idPlastico", idPlastico)
                .spec(requestSpecificationLimites(environment))
                .contentType(ContentType.JSON)
                .when()
                .get("/limits/{idPlastico}")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse criarMajoracaoLimitePorAssociado(Integer idPlastico, CriarMajoracaoLimiteDTO criarMajoracaoLimiteDTO) {
        return given()
                .spec(requestSpecificationLimites(environment))
                .contentType(ContentType.JSON)
                .pathParam("idPlastico", idPlastico)
                .body(criarMajoracaoLimiteDTO)
                .when()
                .post("/limits/{idPlastico}/offer")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }


}

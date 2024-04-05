package Rest.automacao.api.client;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static java.lang.System.*;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.println;


public class Dados {


    public static RequestSpecification requestSpecificationObtemDados;

    public static String obterDados(Integer page) {
        return given()
                .baseUri("https://reqres.in/")
                .basePath("/api/users/")
                .contentType(ContentType.JSON)
                .pathParams("page", page)
        .when()
                .get("{page}")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType("application/json")
        .extract()
                .response()
                .asString();

    }

}

package Rest.automacao.api.factory;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpecificationFactory {

    public static RequestSpecification requestSpecificationObtemDados(String dados) {
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .log(LogDetail.ALL)
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

}

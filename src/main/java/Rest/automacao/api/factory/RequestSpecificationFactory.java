package Rest.automacao.api.factory;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpecificationFactory {

    public static RequestSpecification requestSpecificationLimites(String environment) {
        return new RequestSpecBuilder()
                .setBaseUri("https://cards-limits-v1." + environment + ".sicredi.cloud")
                /*.setBasePath("/api/v1")*/
                .log(LogDetail.ALL)
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static RequestSpecification requestSpecificationCardExtensionPlastic(String environment) {
        return new RequestSpecBuilder()
                .setBaseUri("https://card-extension-plastic." + environment + "-sicredi.in")
                .setBasePath("/api/v1")
                .log(LogDetail.ALL)
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static RequestSpecification requestSpecificationCardsInvoiceCharge() {
        return new RequestSpecBuilder()
                .setBaseUri("https://cards-invoice-charge-v1.uat.sicredi.cloud")
                .log(LogDetail.ALL)
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static RequestSpecification requestSpecificationCardsInvoiceChargeCancelar() {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.uat.sicredi.io/api/v1/crm/cardspct-crm-bff")
                .log(LogDetail.ALL)
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static RequestSpecification requestSpecificationGeraToken() {
        return given().
                contentType(ContentType.JSON)
                .post("https://api.uat.sicredi.io/auth/realms/salesforce/protocol/openid-connect/token")
                .then()
                .extract()
                .response()
                .body().path("access_token");

    }


}

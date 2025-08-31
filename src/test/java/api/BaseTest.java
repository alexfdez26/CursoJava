package api;

import io.restassured.specification.RequestSpecification; // ✅ Import correcto
import net.serenitybdd.rest.SerenityRest;

public class BaseTest {

    protected RequestSpecification givenBase() {
        return SerenityRest
                .given()
                .contentType("application/json")
                .baseUri(System.getProperty("serenity.rest.base.url"));
    }
}

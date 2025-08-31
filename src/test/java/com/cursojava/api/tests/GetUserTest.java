package com.cursojava.api.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class GetUserTest {

    @Test
    public void getUser_shouldReturn200_andValidData() {
        SerenityRest
                .given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue())
                .body("email", containsString("@"));
    }
}

package users;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class DeleteUserTest {

    private EnvironmentVariables environmentVariables;

    @Test
    public void deleteUser_shouldReturnSuccessStatus() {
        String baseUrl = EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty("serenity.rest.base.url");

        SerenityRest
                .given()
                .baseUri(baseUrl)
                .when()
                .delete("/users/1")
                .then()
                .statusCode(200); // JSONPlaceholder devuelve 200 en DELETE
    }
}

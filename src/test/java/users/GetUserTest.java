package users;

import models.Usuario;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class GetUserTest {

    private EnvironmentVariables environmentVariables;

    @Test
    public void getUser_shouldReturnUserData() {
        String baseUrl = EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty("serenity.rest.base.url");

        Usuario usuario = SerenityRest
                .given()
                .baseUri(baseUrl)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .extract()
                .as(Usuario.class);

        assertThat(usuario.getId(), equalTo(1));
        assertThat(usuario.getName(), notNullValue());
    }
}

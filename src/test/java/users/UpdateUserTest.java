package users;

import models.Usuario;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class UpdateUserTest {

    private EnvironmentVariables environmentVariables;

    @Test
    public void updateUser_shouldReturnUpdatedUser() {
        String baseUrl = EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty("serenity.rest.base.url");

        Usuario actualizado = new Usuario();
        actualizado.setName("Alex Updated");
        actualizado.setUsername("alexupdated");
        actualizado.setEmail("alex.updated@example.com");

        Usuario respuesta = SerenityRest
                .given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(actualizado)
                .when()
                .put("/users/1")
                .then()
                .statusCode(200)
                .extract()
                .as(Usuario.class);

        assertThat(respuesta.getName(), equalTo(actualizado.getName()));
    }
}

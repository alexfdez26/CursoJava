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
public class CreateUserTest {

    private EnvironmentVariables environmentVariables;

    @Test
    public void createUser_shouldReturnCreatedUser() {
        // Leer la URL base desde serenity.conf
        String baseUrl = EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty("serenity.rest.base.url");

        Usuario nuevo = new Usuario();
        nuevo.setName("Alex Dev");
        nuevo.setUsername("alexdev");
        nuevo.setEmail("alex@example.com");

        Usuario creado = SerenityRest
                .given()
                .baseUri(baseUrl) // Usar la URL base configurada
                .contentType("application/json")
                .body(nuevo)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .as(Usuario.class);

        assertThat(creado.getName(), equalTo(nuevo.getName()));
    }
}

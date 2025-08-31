package users;

import models.Usuario;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class CreateUserTest {

    @Test
    public void createUser_shouldReturnCreatedUser() {
        Usuario nuevo = new Usuario();
        nuevo.setName("Alex Dev");
        nuevo.setUsername("alexdev");
        nuevo.setEmail("alex@example.com");

        Usuario creado = SerenityRest
                .given()
                .contentType("application/json")
                .body(nuevo)
                .when()
                .post("users")
                .then()
                .statusCode(201)
                .extract()
                .as(Usuario.class);

        assertThat(creado.getName(), equalTo(nuevo.getName()));
    }
}

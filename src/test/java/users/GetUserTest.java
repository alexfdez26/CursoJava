package users;

import models.Usuario;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class GetUserTest {

    @Test
    public void getUser_shouldReturnUsuario() {
        Usuario usuario = SerenityRest
                .given()
                .when()
                .get("users/1")
                .then()
                .statusCode(200)
                .extract()
                .as(Usuario.class);

        assertThat(usuario.getId(), equalTo(1));
        assertThat(usuario.getName(), notNullValue());
    }
}

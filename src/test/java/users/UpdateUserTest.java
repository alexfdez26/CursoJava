package users;

import models.Usuario;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class UpdateUserTest {

    @Test
    public void updateUser_shouldReturnUpdatedUser() {
        Usuario update = new Usuario();
        update.setId(1);
        update.setName("Alex Updated");
        update.setUsername("alexupdated");
        update.setEmail("alex.updated@example.com");

        Usuario actualizado = SerenityRest
                .given()
                .contentType("application/json")
                .body(update)
                .when()
                .put("users/1")
                .then()
                .statusCode(200)
                .extract()
                .as(Usuario.class);

        assertThat(actualizado.getName(), equalTo(update.getName()));
    }
}
